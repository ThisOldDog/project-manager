package pers.dog.project.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.dog.project.manager.entity.RoleMenu;

import java.util.List;

/**
 * 角色用户持久化
 *
 * @author 废柴 2021/1/25 16:04
 */
@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<RoleMenu> listRoleMenu(@Param("roleId") int roleId);
}
