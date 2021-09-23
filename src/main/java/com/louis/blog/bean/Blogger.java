package com.louis.blog.bean;

import java.security.KeyStore.PrivateKeyEntry;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Profile;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class Blogger {
    //主键
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //简介
    private String profile;
    //昵称
    private String nickname;
    //个性签名
    private String sign;
    //图像名
    private String imageName;

    public Blogger() {
    }

    public Blogger(Integer id, String username, String password, String profile, String nickname, String sign,
            String imageName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.nickname = nickname;
        this.sign = sign;
        this.imageName = imageName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Blogger{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profile='" + profile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sign='" + sign + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
