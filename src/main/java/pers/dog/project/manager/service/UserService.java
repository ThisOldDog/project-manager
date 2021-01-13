package pers.dog.project.manager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pers.dog.project.manager.entity.User;

/**
 * 用户管理
 *
 * @author 废柴 2020/12/27 20:54
 */
public interface UserService {

    Page<User> pageUser(User user, Pageable pageable);

    void storeUser(int userId, String username, String nickname, String email, String avatarUrl);

    void adminUser(int userId, boolean isAdmin);

    void deleteUser(int userId);
}
