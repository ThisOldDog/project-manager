package pers.dog.project.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pers.dog.project.manager.entity.Menu;

import java.util.List;

/**
 * 菜单持久化
 *
 * @author 废柴 2020/12/28 14:18
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query("select m from" +
            " Menu m " +
            "where" +
            "      exists (select 1 " +
            "                from pers.dog.project.manager.entity.User u " +
            "               where u.userId = ?1 and u.admin = true)" +
            "      or exists (select 1 " +
            "                   from pers.dog.project.manager.entity.User u " +
            "                   join pers.dog.project.manager.entity.UserRole ur on ur.userId = u.userId" +
            "                   join pers.dog.project.manager.entity.MenuRole mr on mr.roleId = ur.roleId" +
            "                  where u.userId = ?1 and mr.menuId = m.menuId) " +
            "order by m.sortNumber")
    List<Menu> findByUser(int userId);
}
