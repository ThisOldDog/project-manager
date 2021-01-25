package pers.dog.project.manager.controller;

import org.gitlab4j.api.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dog.project.manager.component.context.SecurityContext;
import pers.dog.project.manager.controller.vo.MenuTreeResponse;
import pers.dog.project.manager.service.MenuService;
import pers.dog.project.manager.service.UserService;

import java.util.List;

/**
 * 用户信息
 *
 * @author 废柴 2020/12/24 16:25
 */
@RestController
@RequestMapping("/user")
public class AccountController {
    private final UserService userService;
    private final MenuService menuService;
    private final SecurityContext securityContext;

    public AccountController(UserService userService,
                             MenuService menuService,
                             SecurityContext securityContext) {
        this.userService = userService;
        this.menuService = menuService;
        this.securityContext = securityContext;
    }

    @GetMapping("/account")
    public User queryUser() {
        User user = securityContext.currentUser();
        userService.storeUser(user.getId(), user.getUsername(), user.getName(), user.getEmail(), user.getAvatarUrl());
        return user;
    }

    @GetMapping("/menu/tree")
    public List<MenuTreeResponse> treeMenu() {
        return menuService.treeMenu(securityContext.currentUser().getId());
    }
}
