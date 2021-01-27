package pers.dog.project.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pers.dog.project.manager.configuration.GitlabProperties;
import pers.dog.project.manager.configuration.IamProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        GitlabProperties.class,
        IamProperties.class
})
@MapperScan("pers.dog.project.manager.mapper")
public class ProjectManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagerApplication.class, args);
    }

}
