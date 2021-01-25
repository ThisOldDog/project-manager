package pers.dog.project.manager.service;

import pers.dog.project.manager.controller.vo.RoleTreeResponse;
import pers.dog.project.manager.entity.Role;

import java.util.List;

/**
 * 角色管理服务
 *
 * @author 废柴 2020/12/27 20:49
 */
public interface RoleService {

    List<RoleTreeResponse> treeRole(Role role);

    Role queryRole(int roleId);

    Role createRole(Role role);

    Role updateRole(Role role);

    void deleteRole(int roleId);
}
