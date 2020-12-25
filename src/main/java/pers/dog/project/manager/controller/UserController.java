package pers.dog.project.manager.controller;

import org.gitlab4j.api.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dog.project.manager.component.context.SecurityContext;

/**
 * 用户信息
 *
 * @author 废柴 2020/12/24 16:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final SecurityContext securityContext;

    public UserController(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @GetMapping
    public User queryUser() {
        return securityContext.currentUser();
    }
}
