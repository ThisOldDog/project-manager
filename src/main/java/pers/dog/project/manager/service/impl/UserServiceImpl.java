package pers.dog.project.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import liquibase.util.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.dog.project.manager.configuration.IamProperties;
import pers.dog.project.manager.entity.User;
import pers.dog.project.manager.mapper.UserMapper;
import pers.dog.project.manager.service.UserService;

import java.util.Objects;

/**
 * 用户管理实现类
 *
 * @author 废柴 2020/12/27 20:55
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final IamProperties iamProperties;

    public UserServiceImpl(UserMapper userMapper, IamProperties iamProperties) {
        this.userMapper = userMapper;
        this.iamProperties = iamProperties;
    }

    @Override
    public IPage<User> pageUser(User user, Page<User> page) {
        return userMapper.selectPage(page,
                new QueryWrapper<>(new User())
                        .like(StringUtils.hasText(user.getUsername()), "USERNAME", user.getUsername())
                        .like(StringUtils.hasText(user.getEmail()), "EMAIL", user.getEmail())
                        .eq(BooleanUtils.isTrue(user.getAdmin()), "IS_ADMIN", true));
    }

    @Override
    public void storeUser(int userId, String username, String nickname, String email, String avatarUrl) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            userMapper.insert(new User()
                    .setUserId(userId)
                    .setUsername(username)
                    .setNickname(nickname)
                    .setAdmin(Objects.equals(iamProperties.getAdminUsername(), username))
                    .setEmail(email)
                    .setAvatarUrl(avatarUrl));
        } else {
            userMapper.updateById(new User()
                    .setUserId(userId)
                    .setUsername(username)
                    .setNickname(nickname)
                    .setAdmin(Objects.equals(iamProperties.getAdminUsername(), username))
                    .setEmail(email)
                    .setAvatarUrl(avatarUrl));
        }
    }

    @Override
    public void adminUser(int userId, boolean isAdmin) {
        userMapper.updateById(new User().setUserId(userId).setAdmin(isAdmin));
    }

    @Override
    public void deleteUser(int userId) {
        userMapper.deleteById(userId);
    }
}
