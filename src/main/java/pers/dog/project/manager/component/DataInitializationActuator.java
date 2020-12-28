package pers.dog.project.manager.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import pers.dog.project.manager.configuration.DataInitializationProperties;
import pers.dog.project.manager.entity.Property;
import pers.dog.project.manager.repository.PropertyRepository;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 数据初始化执行器
 *
 * @author 废柴 2020/12/28 15:26
 */
@Component
public class DataInitializationActuator implements SmartLifecycle {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializationActuator.class);
    private static final String DATA_INITIALIZATION_CODE = "data.initialization";
    private static final DateTimeFormatter DATA_INITIALIZATION_VALUE_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    private final AtomicBoolean started = new AtomicBoolean(false);

    private final PropertyRepository propertyRepository;
    private final DataSource dataSource;
    private final DataInitializationProperties dataInitializationProperties;

    public DataInitializationActuator(PropertyRepository propertyRepository,
                                      DataSource dataSource,
                                      DataInitializationProperties dataInitializationProperties) {
        this.propertyRepository = propertyRepository;
        this.dataSource = dataSource;
        this.dataInitializationProperties = dataInitializationProperties;
    }

    @Override
    public void start() {
        if (started.compareAndSet(false, true)) {
            logger.info("[Data Initialization] start ...");
            Optional<Property> property = propertyRepository.findByPropertyCode(DATA_INITIALIZATION_CODE);
            if (property.isEmpty()) {
                try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
                    try {
                        String dmlScript = Files.readAllLines(
                                ResourceUtils.getFile(dataInitializationProperties.getDmlScriptPath()).toPath(),
                                Charset.forName(dataInitializationProperties.getDmlScriptCharset()))
                                .stream()
                                .filter(line -> !line.startsWith("--"))
                                .collect(Collectors.joining(" "));
                        connection.setAutoCommit(false);
                        for (String dml : dmlScript.split(";")) {
                            if (StringUtils.hasText(dml)) {
                                logger.debug("[Data Initialization] DML : {}", dml.trim());
                                statement.addBatch(dml.trim());
                            }
                        }
                        statement.executeBatch();
                        connection.commit();
                        propertyRepository.save(new Property()
                                .setPropertyCode(DATA_INITIALIZATION_CODE)
                                .setPropertyValue(LocalDateTime.now().format(DATA_INITIALIZATION_VALUE_FORMATTER)));
                    } catch (FileNotFoundException e) {
                        throw new IllegalArgumentException("[Data Initialization] File not found " + dataInitializationProperties.getDmlScriptPath(), e);
                    } catch (IOException e) {
                        throw new IllegalArgumentException("[Data Initialization] An error occurred while reading the file " + dataInitializationProperties.getDmlScriptPath(), e);
                    }
                } catch (SQLException e) {
                    throw new IllegalStateException("[Data Initialization] An error occurred while getting the database connection.", e);
                }
            }
            logger.info("[Data Initialization] completed ...");
        }
    }

    @Override
    public void stop() {
        // ignore
    }

    @Override
    public boolean isRunning() {
        return started.get();
    }
}
