package pers.dog.project.manager.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pers.dog.project.manager.constant.ApplicationConstants;

import javax.validation.constraints.NotEmpty;

/**
 * 数据初始化配置
 *
 * @author 废柴 2020/12/28 15:32
 */
@ConfigurationProperties(ApplicationConstants.PROJECT_MANAGER + ".data-initialization")
public class DataInitializationProperties {
    @NotEmpty
    private String dmlScriptPath = "classpath:data.sql";
    @NotEmpty
    private String dmlScriptCharset = "utf-8";

    public String getDmlScriptPath() {
        return dmlScriptPath;
    }

    public DataInitializationProperties setDmlScriptPath(String dmlScriptPath) {
        this.dmlScriptPath = dmlScriptPath;
        return this;
    }

    public String getDmlScriptCharset() {
        return dmlScriptCharset;
    }

    public DataInitializationProperties setDmlScriptCharset(String dmlScriptCharset) {
        this.dmlScriptCharset = dmlScriptCharset;
        return this;
    }
}
