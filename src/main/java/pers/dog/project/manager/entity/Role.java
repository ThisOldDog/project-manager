package pers.dog.project.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;

/**
 * 角色实体
 *
 * @author 废柴 2020/12/28 14:25
 */
@TableName("PM_ROLE")
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer roleId;
    private String roleCode;
    private String roleName;
    private Integer parentId;
    @TableField(value = "IS_ADMIN")
    private Boolean admin;

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

    public Integer getParentId() {
        return parentId;
    }

    public Role setParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public Role setAdmin(Boolean admin) {
        this.admin = admin;
        return this;
    }
}
