package pers.dog.project.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.dog.project.manager.entity.User;

/**
 * 用户持久化
 *
 * @author 废柴 2020/12/27 20:57
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
