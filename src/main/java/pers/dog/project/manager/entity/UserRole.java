package pers.dog.project.manager.entity;

import javax.persistence.*;

/**
 * 用户角色实体
 *
 * @author 废柴 2020/12/28 14:26
 */
@Entity
@Table(name = "PM_ROLE_ROLE", uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "roleId"}))
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userRoleId;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Integer roleId;

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public UserRole setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserRole setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public UserRole setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }
}
