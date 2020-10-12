package com.jxny.bean;

public class AnimeUser {
    private Integer id; //id自增
    private String username; // 用户名
    private String email; //  邮箱
    private String password; // 密码
   //private String history; // 浏览纪录 xxx,xxx,xxx 用逗号隔开 取得时候用字符分割方法


    public AnimeUser() {
    }

    public AnimeUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AnimeUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public AnimeUser(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {  //用于测试 数据库连接是否正常
        return "AnimeUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
