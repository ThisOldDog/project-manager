package pers.dog.project.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import pers.dog.project.manager.constant.ApplicationConstants;
import pers.dog.project.manager.constant.MenuType;
import pers.dog.project.manager.controller.vo.MenuTreeResponse;
import pers.dog.project.manager.entity.Menu;
import pers.dog.project.manager.mapper.MenuMapper;
import pers.dog.project.manager.service.MenuService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单管理实现类
 *
 * @author 废柴 2020/12/27 20:52
 */
@Service
public class MenuServiceImpl implements MenuService {
    private final MenuMapper menuMapper;

    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<MenuTreeResponse> treeMenu(Menu menu) {
        return filter(tree(menuMapper.selectList(null)), menu);
    }

    @Override
    public List<MenuTreeResponse> treeMenu(int userId) {
        return tree(menuMapper.listAssessableMenu(userId));
    }

    @Override
    public Menu queryMenu(int menuId) {
        return Optional.ofNullable(menuMapper.selectById(menuId)).orElseThrow(() -> new IllegalArgumentException("查询的菜单不存在"));
    }

    @Override
    public Menu createMenu(Menu menu) {
        if (menu.setParentId(Optional.ofNullable(menu.getParentId()).orElse(0)).getParentId() > 0) {
            Assert.isTrue(menuMapper.selectCount(new QueryWrapper<>(new Menu()).eq("MENU_ID", menu.getParentId())) == 1, "上级菜单不存在");
        }
        if (MenuType.PAGE.equals(menu.getMenuType())) {
            Assert.notNull(menu.getPageRoute(), "页面类型的菜单必须填写页面路由");
        } else {
            menu.setPageRoute(null);
        }
        menuMapper.insert(menu);
        return menu;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        menuMapper.updateById(menu);
        return menu;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(int menuId) {
        menuMapper.deleteById(menuId);
        deleteSubMenus(menuMapper.selectList(
                new QueryWrapper<>(new Menu())
                        .eq("PARENT_ID", menuId)));
    }

    private void deleteSubMenus(List<Menu> menus) {
        if (menus.isEmpty()) {
            return;
        }
        menus.forEach(menu -> deleteMenu(menu.getMenuId()));
    }

    private List<MenuTreeResponse> filter(List<MenuTreeResponse> menuList, Menu condition) {
        if (condition == null || (
                !StringUtils.hasText(condition.getMenuCode())
                        && !StringUtils.hasText(condition.getMenuName())
                        && !StringUtils.hasText(condition.getPageRoute()))) {
            return menuList;
        }
        isMatchAndRemoveUnmatched(menuList, condition);
        return menuList;
    }

    private boolean isMatchAndRemoveUnmatched(List<MenuTreeResponse> menuList, Menu condition) {
        List<MenuTreeResponse> matched = new ArrayList<>();
        List<MenuTreeResponse> unmatched = new ArrayList<>();
        menuList.forEach(menuResponse -> {
            // match
            if (like(menuResponse.getMenuCode(), condition.getMenuCode())
                    || like(menuResponse.getMenuName(), condition.getMenuName())
                    || like(menuResponse.getPageRoute(), condition.getPageRoute())) {
                matched.add(menuResponse);
                return;
            }
            // child match
            if (menuResponse.getSubMenuList() != null && isMatchAndRemoveUnmatched(menuResponse.getSubMenuList(), condition)) {
                matched.add(menuResponse);
            } else {
                unmatched.add(menuResponse);
            }
        });
        menuList.removeAll(unmatched);
        return !matched.isEmpty();
    }

    private boolean like(String value, String pattern) {
        return value != null && pattern != null && value.contains(pattern);
    }

    private List<MenuTreeResponse> tree(List<Menu> menuList) {
        if (menuList.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Integer, List<Menu>> parentMenuList = menuList.stream()
                .collect(Collectors.groupingBy(Menu::getParentId, TreeMap::new, Collectors.toList()));
        return tree(new ArrayList<>(), parentMenuList, ApplicationConstants.ROOT_PARENT_ID, null);
    }

    private List<MenuTreeResponse> tree(List<MenuTreeResponse> result, Map<Integer, List<Menu>> parentMenuList, int rootParentId, String parentName) {
        List<Menu> menus = parentMenuList.get(rootParentId);
        if (menus != null) {
            result.addAll(menus.stream()
                    .map(menu -> {
                        MenuTreeResponse menuTreeResponse = new MenuTreeResponse();
                        BeanUtils.copyProperties(menu, menuTreeResponse);
                        menuTreeResponse.setParentName(parentName);
                        List<MenuTreeResponse> child = new ArrayList<>();
                        menuTreeResponse.setSubMenuList(child);
                        tree(child, parentMenuList, menu.getMenuId(), menu.getMenuName());
                        return menuTreeResponse;
                    })
                    .sorted(Comparator.comparingInt(Menu::getSortNumber))
                    .collect(Collectors.toList()));
        }
        return result;
    }
}
