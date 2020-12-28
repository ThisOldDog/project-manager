package pers.dog.project.manager.service;

import pers.dog.project.manager.controller.vo.MenuResponse;

import java.util.List;

/**
 * 菜单管理服务
 * @author 废柴 2020/12/27 20:49
 */
public interface MenuService {

    List<MenuResponse> treeMenu(int userId);
}
