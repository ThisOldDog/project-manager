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
    @Column(length = 128, nullable = false, unique = true)
    private String username;
    @Column(name = "is_admin", nullable = false)
    private Boolean admin;

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
}
