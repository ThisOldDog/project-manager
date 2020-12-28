package pers.dog.project.manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dog.project.manager.component.context.SecurityContext;
import pers.dog.project.manager.controller.vo.MenuResponse;
import pers.dog.project.manager.service.MenuService;

import java.util.List;

/**
 * 菜单管理
 *
 * @author 废柴 2020/12/27 20:46
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;
    private final SecurityContext securityContext;

    public MenuController(MenuService menuService, SecurityContext securityContext) {
        this.menuService = menuService;
        this.securityContext = securityContext;
    }

    @GetMapping("/tree")
    public List<MenuResponse> treeMenu() {
        return menuService.treeMenu(securityContext.currentUser().getId());
    }
}
