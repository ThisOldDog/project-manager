package pers.dog.project.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色用户实体
 *
 * @author 废柴 2020/12/28 14:26
 */
@TableName("PM_ROLE_USER")
public class RoleUser {
    @TableId(type = IdType.AUTO)
    private Integer roleUserId;
    private Integer roleId;
    private Integer userId;

    @TableField(exist = false)
    private User user;

    public Integer getRoleUserId() {
        return roleUserId;
    }

    public RoleUser setRoleUserId(Integer roleUserId) {
        this.roleUserId = roleUserId;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public RoleUser setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public RoleUser setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RoleUser setUser(User user) {
        this.user = user;
        return this;
    }
}
