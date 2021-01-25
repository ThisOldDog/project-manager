package pers.dog.project.manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.dog.project.manager.controller.vo.RoleTreeResponse;
import pers.dog.project.manager.entity.Role;
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

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
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
}
