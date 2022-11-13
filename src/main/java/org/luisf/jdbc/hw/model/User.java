package org.luisf.jdbc.hw.model;

public class User {
    private Long id;
    private String username;
    private String pwd;
    private String email;

    public User(Long id, String username, String pwd, String email) {
        this.username = username;
        this.id = id;
        this.pwd = pwd;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
