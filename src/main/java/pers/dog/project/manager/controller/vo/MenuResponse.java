package pers.dog.project.manager.controller.vo;

import pers.dog.project.manager.entity.Menu;

import java.util.List;

/**
 * 菜单查询响应结果
 *
 * @author 废柴 2020/12/27 20:48
 */
public class MenuResponse extends Menu {
    private List<MenuResponse> subMenuList;

    public List<MenuResponse> getSubMenuList() {
        return subMenuList;
    }

    public MenuResponse setSubMenuList(List<MenuResponse> subMenuList) {
        this.subMenuList = subMenuList;
        return this;
    }
}
