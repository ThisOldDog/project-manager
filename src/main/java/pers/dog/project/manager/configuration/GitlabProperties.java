package pers.dog.project.manager.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pers.dog.project.manager.constant.ApplicationConstants;

/**
 * Gitlab 配置属性
 *
 * @author 废柴 2020/12/24 16:53
 */
@ConfigurationProperties(ApplicationConstants.PROJECT_MANAGER + ".gitlab")
public class GitlabProperties {
    private String home;

    public String getHome() {
        return home;
    }

    public GitlabProperties setHome(String home) {
        this.home = home;
        return this;
    }
}
