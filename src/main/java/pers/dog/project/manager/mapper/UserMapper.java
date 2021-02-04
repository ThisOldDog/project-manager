package pers.dog.project.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.dog.project.manager.entity.User;

/**
 * 用户持久化
 *
 * @author 废柴 2020/12/27 20:57
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    IPage<User> listUser(@Param("user") User user,
                         @Param("withoutRoleId") Integer withoutRoleId,
                         @Param("page") Page<User> page);
}
