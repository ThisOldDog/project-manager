package pers.dog.project.manager.exception;

/**
 * 401 未授权
 *
 * @author 废柴 2020/12/24 16:49
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Unauthorized");
    }
}
