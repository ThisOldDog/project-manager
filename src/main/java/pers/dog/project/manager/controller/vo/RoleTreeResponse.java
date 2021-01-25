package pers.dog.project.manager.controller.vo;

import pers.dog.project.manager.entity.Role;

import java.util.List;

/**
 * 角色查询树形响应结果
 *
 * @author 废柴 2020/12/27 20:48
 */
public class RoleTreeResponse extends Role {
    private String parentName;
    private List<RoleTreeResponse> subRoleList;

    public String getParentName() {
        return parentName;
    }

    public RoleTreeResponse setParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public List<RoleTreeResponse> getSubRoleList() {
        return subRoleList;
    }

    public RoleTreeResponse setSubRoleList(List<RoleTreeResponse> subRoleList) {
        this.subRoleList = subRoleList;
        return this;
    }
}
