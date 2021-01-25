package pers.dog.project.manager.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pers.dog.project.manager.constant.ApplicationConstants;
import pers.dog.project.manager.controller.vo.RoleTreeResponse;
import pers.dog.project.manager.entity.Role;
import pers.dog.project.manager.repository.RoleRepository;
import pers.dog.project.manager.service.RoleService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色管理实现类
 *
 * @author 废柴 2020/12/27 20:52
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleTreeResponse> treeRole(Role role) {
        return tree(roleRepository.findAll());
    }

    @Override
    public Role queryRole(int roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("查询的角色不存在"));
    }

    @Override
    public Role createRole(Role role) {
        if (role.setParentId(Optional.ofNullable(role.getParentId()).orElse(0)).getParentId() > 0) {
            Assert.isTrue(roleRepository.count(Example.of(new Role().setRoleId(role.getParentId()))) == 1, "上级角色不存在");
        }
        // 管理员角色仅允许通过种子数据导入
        role.setAdministrator(false);
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteRole(int roleId) {
        roleRepository.deleteById(roleId);
        deleteSubRoles(roleRepository.findAll(Example.of(new Role().setParentId(roleId))));
    }

    private void deleteSubRoles(List<Role> roles) {
        if (roles.isEmpty()) {
            return;
        }
        roles.forEach(role -> deleteRole(role.getRoleId()));
    }

    private List<RoleTreeResponse> tree(List<Role> roleList) {
        if (roleList.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Integer, List<Role>> parentRoleList = roleList.stream()
                .collect(Collectors.groupingBy(Role::getParentId, TreeMap::new, Collectors.toList()));
        return tree(new ArrayList<>(), parentRoleList, ApplicationConstants.ROOT_PARENT_ID, null);
    }

    private List<RoleTreeResponse> tree(List<RoleTreeResponse> result, Map<Integer, List<Role>> parentRoleList, int rootParentId, String parentName) {
        List<Role> roles = parentRoleList.get(rootParentId);
        if (roles != null) {
            result.addAll(roles.stream()
                    .map(role -> {
                        RoleTreeResponse roleTreeResponse = new RoleTreeResponse();
                        BeanUtils.copyProperties(role, roleTreeResponse);
                        roleTreeResponse.setParentName(parentName);
                        List<RoleTreeResponse> child = new ArrayList<>();
                        roleTreeResponse.setSubRoleList(child);
                        tree(child, parentRoleList, role.getRoleId(), role.getRoleName());
                        return roleTreeResponse;
                    })
                    .sorted(Comparator.comparingInt(Role::getRoleId))
                    .collect(Collectors.toList()));
        }
        return result;
    }
}
