package pers.dog.project.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.dog.project.manager.entity.User;

/**
 * 用户管理
 *
 * @author 废柴 2020/12/27 20:54
 */
public interface UserService {

    IPage<User> pageUser(User user, Page<User> pageable);

    void storeUser(int userId, String username, String nickname, String email, String avatarUrl);

    void adminUser(int userId, boolean isAdmin);

    void deleteUser(int userId);
}
