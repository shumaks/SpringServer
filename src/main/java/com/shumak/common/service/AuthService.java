package com.shumak.common.service;

import com.shumak.common.users.User;

public class AuthService {

    private User currentUser = null;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
}
