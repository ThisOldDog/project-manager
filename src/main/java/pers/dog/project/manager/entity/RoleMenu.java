package pers.dog.project.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import pers.dog.project.manager.util.tree.Child;

/**
 * 角色菜单实体
 *
 * @author 废柴 2020/12/28 14:26
 */
@TableName("PM_ROLE_MENU")
public class RoleMenu extends Child<RoleMenu> {
    @TableId(type = IdType.AUTO)
    private Integer roleMenuId;
    private Integer roleId;
    private Integer menuId;

    @TableField(exist = false)
    private Menu menu;

    public Integer getRoleMenuId() {
        return roleMenuId;
    }

    public RoleMenu setRoleMenuId(Integer roleMenuId) {
        this.roleMenuId = roleMenuId;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public RoleMenu setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public RoleMenu setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public Menu getMenu() {
        return menu;
    }

    public RoleMenu setMenu(Menu menu) {
        this.menu = menu;
        return this;
    }
}
