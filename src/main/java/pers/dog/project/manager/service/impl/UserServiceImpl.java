package pers.dog.project.manager.service.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pers.dog.project.manager.configuration.IamProperties;
import pers.dog.project.manager.entity.User;
import pers.dog.project.manager.repository.UserRepository;
import pers.dog.project.manager.service.UserService;

import java.util.Objects;

/**
 * 用户管理实现类
 *
 * @author 废柴 2020/12/27 20:55
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final IamProperties iamProperties;

    public UserServiceImpl(UserRepository userRepository, IamProperties iamProperties) {
        this.userRepository = userRepository;
        this.iamProperties = iamProperties;
    }

    @Override
    public Page<User> pageUser(User user, Pageable pageable) {
        return userRepository.findAll(
                Example.of(user,
                        ExampleMatcher.matching()
                                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains())),
                pageable);
    }

    @Override
    public void storeUser(int userId, String username, String nickname, String email, String avatarUrl) {
        userRepository.save(new User()
                .setUserId(userId)
                .setUsername(username)
                .setNickname(nickname)
                .setAdmin(Objects.equals(iamProperties.getAdminUsername(), username))
                .setEmail(email)
                .setAvatarUrl(avatarUrl));
    }

    @Override
    public void adminUser(int userId, boolean isAdmin) {
        userRepository.save(
                userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("User not exists."))
                        .setAdmin(isAdmin));
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
