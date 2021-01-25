package pers.dog.project.manager.controller.vo;

import pers.dog.project.manager.entity.Menu;

import java.util.List;

/**
 * 菜单查询树形响应结果
 *
 * @author 废柴 2020/12/27 20:48
 */
public class MenuTreeResponse extends Menu {
    private String parentName;
    private List<MenuTreeResponse> subMenuList;

    public String getParentName() {
        return parentName;
    }

    public MenuTreeResponse setParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public List<MenuTreeResponse> getSubMenuList() {
        return subMenuList;
    }

    public MenuTreeResponse setSubMenuList(List<MenuTreeResponse> subMenuList) {
        this.subMenuList = subMenuList;
        return this;
    }
}
