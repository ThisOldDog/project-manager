package pers.dog.project.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.dog.project.manager.entity.User;

/**
 * 用户持久化
 *
 * @author 废柴 2020/12/27 20:57
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
