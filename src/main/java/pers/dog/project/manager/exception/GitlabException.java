package pers.dog.project.manager.exception;

import org.gitlab4j.api.GitLabApiException;

/**
 * Gitlab 相关的异常
 *
 * @author 废柴 2020/12/24 17:19
 */
public class GitlabException extends RuntimeException {
    private int httpStatus;
    private String message;

    public GitlabException(GitLabApiException exception) {
        this.httpStatus = exception.getHttpStatus();
        this.message = exception.getMessage();
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
