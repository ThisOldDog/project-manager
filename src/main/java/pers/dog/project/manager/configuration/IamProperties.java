package pers.dog.project.manager.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pers.dog.project.manager.constant.ApplicationConstants;

/**
 * 身份认证配置
 *
 * @author 废柴 2020/12/28 10:48
 */
@ConfigurationProperties(ApplicationConstants.PROJECT_MANAGER + ".iam")
public class IamProperties {
    /**
     * 管理员 Gitlab 账号名称
     */
    private String adminUsername;

    public String getAdminUsername() {
        return adminUsername;
    }

    public IamProperties setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
        return this;
    }
}
