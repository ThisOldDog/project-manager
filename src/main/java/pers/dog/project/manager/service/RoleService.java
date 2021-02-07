package pers.dog.project.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.dog.project.manager.controller.vo.RoleTreeResponse;
import pers.dog.project.manager.entity.Role;
import pers.dog.project.manager.entity.RoleMenu;
import pers.dog.project.manager.entity.RoleUser;
import pers.dog.project.manager.entity.User;

import java.util.List;

/**
 * 角色管理服务
 *
 * @author 废柴 2020/12/27 20:49
 */
public interface RoleService {

    List<RoleTreeResponse> treeRole(Role role);

    Role queryRole(int roleId);

    IPage<RoleUser> pageRoleUser(int roleId, User user, Page<RoleUser> page);

    List<RoleMenu> treeRoleMenu(int roleId);

    Role createRole(Role role);

    RoleUser createRoleUser(int roleId, int userId);

    List<RoleUser> createRoleUser(int roleId, List<RoleUser> roleUserList);

    RoleMenu createRoleMenu(int roleId, int menuId);

    Role updateRole(Role role);

    void deleteRole(int roleId);

    void deleteRoleUser(int roleUserId);

    void deleteRoleUser(int roleId, int userId);

    void deleteRoleMenu(int roleId, int menuId);

    void deleteRoleMenu(int roleMenuId);
}
