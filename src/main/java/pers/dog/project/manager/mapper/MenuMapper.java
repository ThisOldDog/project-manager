package pers.dog.project.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.dog.project.manager.entity.Menu;

import java.util.List;

/**
 * 菜单持久化
 *
 * @author 废柴 2020/12/28 14:18
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> listAssessableMenu(@Param("userId") int userId);
}
