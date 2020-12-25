package pers.dog.project.manager.component.context;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pers.dog.project.manager.component.GitlabClientFactory;
import pers.dog.project.manager.exception.GitlabException;
import pers.dog.project.manager.exception.UnauthorizedException;

import java.util.Optional;

/**
 * 安全上下文
 *
 * @author 废柴 2020/12/24 16:43
 */
@Component
public class SecurityContext {
    private final ThreadLocal<String> accessToken = new ThreadLocal<>();
    private final GitlabClientFactory gitlabClientFactory;

    public SecurityContext(GitlabClientFactory gitlabClientFactory) {
        this.gitlabClientFactory = gitlabClientFactory;
    }

    public Optional<String> accessToken() {
        return Optional.ofNullable(accessToken.get());
    }

    public String requireAccessToken() {
        return accessToken().orElseThrow(UnauthorizedException::new);
    }

    public void storeAccessToken(String accessTokenValue) {
        if (StringUtils.hasText(accessTokenValue)) {
            if (accessTokenValue.startsWith("bearer ")) {
                accessToken.set(accessTokenValue.substring(7));
            } else {
                accessToken.set(accessTokenValue);
            }
        } else {
            accessToken.remove();
        }
    }

    public User currentUser() {
        try {
            return gitlabClientFactory.getInstance(requireAccessToken()).getUserApi().getCurrentUser();
        } catch (GitLabApiException e) {
            throw new GitlabException(e);
        }
    }
}
