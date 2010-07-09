package com.pb.hiring.controllers.util;

import com.pb.hiring.model.User;

/**
 * Request-scoped bean that holds objects relevant to the current request,
 * including the logged-in user.
 * 
 * @author pbourke
 */
public class UserRequestContext {
    public User getUser() {
        return null;
    }
    
    public boolean isLoggedIn() {
        return getUser() != null;
    }
}
