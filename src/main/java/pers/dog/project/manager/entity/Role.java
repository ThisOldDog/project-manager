package pers.dog.project.manager.entity;

import javax.persistence.*;

/**
 * 角色实体
 *
 * @author 废柴 2020/12/28 14:25
 */
@Entity
@Table(name = "PM_ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Column(length = 32, nullable = false, updatable = false, unique = true)
    private String roleCode;
    @Column(length = 32, nullable = false)
    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public Role setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public Role setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public Role setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }
}