package pers.dog.project.manager.service;

import pers.dog.project.manager.controller.vo.MenuTreeResponse;
import pers.dog.project.manager.entity.Menu;

import java.util.List;

/**
 * 菜单管理服务
 *
 * @author 废柴 2020/12/27 20:49
 */
public interface MenuService {

    List<MenuTreeResponse> treeMenu(Menu menu);

    List<MenuTreeResponse> treeMenu(int userId);

    Menu queryMenu(int menuId);

    Menu createMenu(Menu menu);

    Menu updateMenu(Menu menu);

    void deleteMenu(int menuId);
}
