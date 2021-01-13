package pers.dog.project.manager.entity;

import javax.persistence.*;

/**
 * 用户
 *
 * @author 废柴 2020/12/27 20:52
 */
@Entity
@Table(name = "PM_USER")
public class User {
    @Id
    private Integer userId;
    @Column(length = 128, nullable = false)
    private String username;
    @Column(name = "is_admin", nullable = false)
    private Boolean admin;
    @Column(length = 128, nullable = false)
    private String nickname;
    @Column(length = 256)
    private String email;
    @Column(length = 1024)
    private String avatarUrl;

    public Integer getUserId() {
        return userId;
    }

    public User setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public User setAdmin(Boolean admin) {
        this.admin = admin;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public User setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }
}
