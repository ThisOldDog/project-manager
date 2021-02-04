package pers.dog.project.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import pers.dog.project.manager.constant.ApplicationConstants;
import pers.dog.project.manager.controller.vo.RoleTreeResponse;
import pers.dog.project.manager.entity.Role;
import pers.dog.project.manager.entity.RoleUser;
import pers.dog.project.manager.entity.User;
import pers.dog.project.manager.mapper.RoleMapper;
import pers.dog.project.manager.mapper.RoleUserMapper;
import pers.dog.project.manager.service.RoleService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色管理实现类
 *
 * @author 废柴 2020/12/27 20:52
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleUserMapper roleUserMapper;

    public RoleServiceImpl(RoleMapper roleMapper,
                           RoleUserMapper roleUserMapper) {
        this.roleMapper = roleMapper;
        this.roleUserMapper = roleUserMapper;
    }

    @Override
    public List<RoleTreeResponse> treeRole(Role role) {
        return tree(roleMapper.selectList(null));
    }

    @Override
    public Role queryRole(int roleId) {
        return Optional.ofNullable(roleMapper.selectById(roleId))
                .orElseThrow(() -> new IllegalArgumentException("查询的角色不存在"));
    }

    @Override
    public IPage<RoleUser> pageRoleUser(int roleId, User user, Page<RoleUser> page) {
        return roleUserMapper.listRoleUser(roleId, user, page);
    }

    @Override
    public Role createRole(Role role) {
        if (role.setParentId(Optional.ofNullable(role.getParentId()).orElse(0)).getParentId() > 0) {
            Assert.isTrue(roleMapper.selectCount(
                    new QueryWrapper<>(new Role()).eq("ROLE_ID", role.getParentId())) == 1,
                    "上级角色不存在");
        }
        // 管理员角色仅允许通过种子数据导入
        role.setAdmin(false);
        roleMapper.insert(role);
        return role;
    }

    @Override
    public RoleUser createRoleUser(int roleId, int userId) {
        RoleUser roleUser = new RoleUser().setRoleId(roleId).setUserId(userId);
        roleUserMapper.insert(roleUser);
        return roleUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RoleUser> createRoleUser(int roleId, List<RoleUser> roleUserList) {
        if (CollectionUtils.isEmpty(roleUserList)) {
            return roleUserList;
        }
        Set<Integer> exists = roleUserMapper.selectList(new QueryWrapper<>(new RoleUser())
                .eq("ROLE_ID", roleId)
                .in("USER_ID", roleUserList.stream().map(RoleUser::getUserId).collect(Collectors.toList())))
                .stream()
                .map(RoleUser::getUserId)
                .collect(Collectors.toSet());
        roleUserList.forEach(roleUser -> {
            if (!exists.contains(roleUser.getUserId())) {
                roleUserMapper.insert(roleUser.setRoleId(roleId));
            }
        });
        return roleUserList;
    }

    @Override
    public Role updateRole(Role role) {
        roleMapper.updateById(role);
        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(int roleId) {
        roleMapper.deleteById(roleId);
        deleteSubRoles(roleMapper.selectList(new QueryWrapper<>(new Role()).eq("PARENT_ID", roleId)));
    }

    @Override
    public void deleteRoleUser(int roleUserId) {
        roleUserMapper.deleteById(roleUserId);
    }

    @Override
    public void deleteRoleUser(int roleId, int userId) {
        roleUserMapper.delete(new QueryWrapper<>(new RoleUser()).eq("ROLE_ID", roleId).eq("USER_ID", userId));
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
