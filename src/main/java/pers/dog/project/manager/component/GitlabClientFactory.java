package pers.dog.project.manager.component;

import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApi;
import org.springframework.stereotype.Component;
import pers.dog.project.manager.configuration.GitlabProperties;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Gitlab 客户端创建工厂
 *
 * @author 废柴 2020/12/24 17:02
 */
@Component
public class GitlabClientFactory {
    private final GitlabProperties gitlabProperties;
    private final Map<String, GitLabApi> clientCache = new WeakHashMap<>();

    public GitlabClientFactory(GitlabProperties gitlabProperties) {
        this.gitlabProperties = gitlabProperties;
    }

    public GitLabApi getInstance(String accessToken) {
        GitLabApi gitLabApi = clientCache.get(accessToken);
        if (gitLabApi != null) {
            return gitLabApi;
        }
        gitLabApi = new GitLabApi(gitlabProperties.getHome(), Constants.TokenType.OAUTH2_ACCESS, accessToken);
        clientCache.put(accessToken, gitLabApi);
        return gitLabApi;
    }
}
