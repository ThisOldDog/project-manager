package pers.dog.project.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.dog.project.manager.entity.Role;

/**
 * 角色持久化
 *
 * @author 废柴 2021/1/22 14:34
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
