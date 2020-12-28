package pers.dog.project.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pers.dog.project.manager.entity.Property;

import java.util.Optional;

/**
 * 菜单持久化
 *
 * @author 废柴 2020/12/28 14:18
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    Optional<Property> findByPropertyCode(String propertyCode);
}
