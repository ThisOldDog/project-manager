package pers.dog.project.manager.entity;

import javax.persistence.*;

/**
 * 菜单角色实体
 *
 * @author 废柴 2020/12/28 14:26
 */
@Entity
@Table(name = "PM_MENU_ROLE", uniqueConstraints = @UniqueConstraint(columnNames = {"menuId", "roleId"}))
public class MenuRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuRoleId;
    @Column(nullable = false)
    private Integer menuId;
    @Column(nullable = false)
    private Integer roleId;

    public Integer getMenuRoleId() {
        return menuRoleId;
    }

    public MenuRole setMenuRoleId(Integer menuRoleId) {
        this.menuRoleId = menuRoleId;
        return this;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public MenuRole setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public MenuRole setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }
}
