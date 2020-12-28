package pers.dog.project.manager.service.impl;

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
    public void storeUser(int userId, String username) {
        userRepository.save(new User()
                .setUserId(userId)
                .setUsername(username)
                .setAdmin(Objects.equals(iamProperties.getAdminUsername(), username)));
    }
}
