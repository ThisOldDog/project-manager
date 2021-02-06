package pers.dog.project.manager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.dog.project.manager.entity.User;
import pers.dog.project.manager.service.UserService;

/**
 * 用户信息
 *
 * @author 废柴 2020/12/24 16:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public IPage<User> pageUser(User user,
                                @RequestParam(required = false) Integer withoutRoleId,
                                @RequestParam(required = false) Boolean withoutAdmin,
                                Page<User> page) {
        return userService.pageUser(user, withoutRoleId, withoutAdmin, page);
    }

    @PutMapping("/cancel-admin/{userId}")
    public ResponseEntity<Void> cancelAdmin(@PathVariable int userId) {
        userService.adminUser(userId, false);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/assign-admin/{userId}")
    public ResponseEntity<Void> assignAdmin(@PathVariable int userId) {
        userService.adminUser(userId, true);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
