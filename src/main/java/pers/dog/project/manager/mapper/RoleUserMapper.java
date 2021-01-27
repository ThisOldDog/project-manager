package pers.dog.project.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.dog.project.manager.entity.RoleUser;
import pers.dog.project.manager.entity.User;

/**
 * 角色用户持久化
 *
 * @author 废柴 2021/1/25 16:04
 */
@Repository
public interface RoleUserMapper extends BaseMapper<RoleUser> {

    IPage<RoleUser> listRoleUser(@Param("roleId") int roleId,
                                 @Param("user") User user,
                                 @Param("page") Page<RoleUser> page);
}
