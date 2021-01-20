package pers.dog.project.manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.dog.project.manager.controller.vo.MenuResponse;
import pers.dog.project.manager.entity.Menu;
import pers.dog.project.manager.service.MenuService;

import java.util.List;
import java.util.Optional;

/**
 * 菜单管理
 *
 * @author 废柴 2020/12/27 20:46
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/tree")
    public List<MenuResponse> treeMenu(Menu menu) {
        return menuService.treeMenu(menu);
    }

    @GetMapping("{menuId}")
    public Menu queryMenu(@PathVariable int menuId) {
        return menuService.queryMenu(menuId);
    }

    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    @PutMapping("/{menuId}")
    public Menu updateMenu(@PathVariable int menuId,
                           @RequestBody Menu menu) {
        return menuService.updateMenu(menu.setMenuId(menuId));
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable int menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.noContent().build();
    }
}
