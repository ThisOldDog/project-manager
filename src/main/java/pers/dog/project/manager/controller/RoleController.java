package pers.dog.project.manager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.dog.project.manager.controller.vo.RoleTreeResponse;
import pers.dog.project.manager.entity.Role;
import pers.dog.project.manager.entity.RoleUser;
import pers.dog.project.manager.entity.User;
import pers.dog.project.manager.service.RoleService;

import java.util.List;

/**
 * 角色管理
 *
 * @author 废柴 2020/12/27 20:46
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/tree")
    public List<RoleTreeResponse> treeRole(Role role) {
        return roleService.treeRole(role);
    }

    @GetMapping("{roleId}")
    public Role queryRole(@PathVariable int roleId) {
        return roleService.queryRole(roleId);
    }

    @GetMapping("/{roleId}/user")
    public IPage<RoleUser> queryRoleUser(@PathVariable int roleId,
                                         User user,
                                         Page<RoleUser> page) {
        return roleService.pageRoleUser(roleId, user, page);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PostMapping("/{roleId}/user/{userId}")
    public RoleUser createRoleUser(@PathVariable int roleId,
                                   @PathVariable int userId) {
        return roleService.createRoleUser(roleId, userId);
    }

    @PostMapping("/{roleId}/user")
    public List<RoleUser> createRoleUser(@PathVariable int roleId,
                                         @RequestBody List<RoleUser> roleUserList) {
        return roleService.createRoleUser(roleId, roleUserList);
    }

    @PutMapping("/{roleId}")
    public Role updateRole(@PathVariable int roleId,
                           @RequestBody Role role) {
        return roleService.updateRole(role.setRoleId(roleId));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable int roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{roleUserId}")
    public ResponseEntity<Void> deleteRoleUser(@PathVariable int roleUserId) {
        roleService.deleteRoleUser(roleUserId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{roleId}/user/{userId}")
    public ResponseEntity<Void> deleteRoleUser(@PathVariable int roleId,
                                               @PathVariable int userId) {
        roleService.deleteRoleUser(roleId, userId);
        return ResponseEntity.noContent().build();
    }
}
