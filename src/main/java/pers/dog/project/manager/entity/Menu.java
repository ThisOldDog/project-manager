package pers.dog.project.manager.entity;

import pers.dog.project.manager.constant.MenuType;

import javax.persistence.*;

/**
 * 菜单
 *
 * @author 废柴 2020/12/27 20:34
 */
@Entity
@Table(name = "PM_MENU")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;

    @Column(length = 32, updatable = false, unique = true, nullable = false)
    private String menuCode;

    @Column(length = 32, nullable = false)
    private String menuName;

    @Column(nullable = false)
    private Integer sortNumber;

    @Column(length = 32, updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    @Column(nullable = false)
    private Integer parentId;

    @Column(length = 128)
    private String pageRoute;

    public Integer getMenuId() {
        return menuId;
    }

    public Menu setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public Menu setMenuCode(String menuCode) {
        this.menuCode = menuCode;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public Menu setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public Menu setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
        return this;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public Menu setMenuType(MenuType menuType) {
        this.menuType = menuType;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Menu setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getPageRoute() {
        return pageRoute;
    }

    public Menu setPageRoute(String pageRoute) {
        this.pageRoute = pageRoute;
        return this;
    }
}
