package pers.dog.project.manager.controller;

import org.gitlab4j.api.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dog.project.manager.component.context.SecurityContext;
import pers.dog.project.manager.service.UserService;

/**
 * 用户信息
 *
 * @author 废柴 2020/12/24 16:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final SecurityContext securityContext;

    public UserController(UserService userService, SecurityContext securityContext) {
        this.userService = userService;
        this.securityContext = securityContext;
    }

    @GetMapping
    public User queryUser() {
        User user = securityContext.currentUser();
        userService.storeUser(user.getId(), user.getName());
        return user;
    }
}
