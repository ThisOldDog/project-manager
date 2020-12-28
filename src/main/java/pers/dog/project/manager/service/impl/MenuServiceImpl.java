package pers.dog.project.manager.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.dog.project.manager.controller.vo.MenuResponse;
import pers.dog.project.manager.entity.Menu;
import pers.dog.project.manager.repository.MenuRepository;
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
    private static final int ROOT_PARENT_ID = 0;

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuResponse> treeMenu(int userId) {
        return tree(menuRepository.findByUser(userId));
    }

    private List<MenuResponse> tree(List<Menu> menuList) {
        if (menuList.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Integer, List<Menu>> parentMenuList = menuList.stream().collect(Collectors.groupingBy(Menu::getParentId, TreeMap::new, Collectors.toList()));
        return tree(new ArrayList<>(), parentMenuList, ROOT_PARENT_ID);
    }

    private List<MenuResponse> tree(List<MenuResponse> result, Map<Integer, List<Menu>> parentMenuList, int rootParentId) {
        List<Menu> menus = parentMenuList.get(rootParentId);
        if (menus != null) {
            result.addAll(menus.stream().map(menu -> {
                MenuResponse menuResponse = new MenuResponse();
                BeanUtils.copyProperties(menu, menuResponse);
                List<MenuResponse> child = new ArrayList<>();
                menuResponse.setSubMenuList(child);
                tree(child, parentMenuList, menu.getMenuId());
                return menuResponse;
            }).collect(Collectors.toList()));
        }
        return result;
    }
}
