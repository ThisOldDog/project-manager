package pers.dog.project.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import pers.dog.project.manager.constant.MenuType;

/**
 * 菜单
 *
 * @author 废柴 2020/12/27 20:34
 */
@TableName("PM_MENU")
public class Menu {

    @TableId(type = IdType.AUTO)
    private Integer menuId;
    private String menuCode;
    private String menuName;
    private String menuIcon;
    private Integer sortNumber;
    private MenuType menuType;
    private Integer parentId;
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

    public String getMenuIcon() {
        return menuIcon;
    }

    public Menu setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
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
