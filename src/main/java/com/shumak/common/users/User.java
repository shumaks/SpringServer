package com.shumak.common.users;

public class User {
    private long id;
    private String login;
    private String password;

    private String accessRights;

    public User(long id, String login, String password, String accessRights) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.accessRights = accessRights;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }
}
