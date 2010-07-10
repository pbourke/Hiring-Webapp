package com.pb.hiring.controllers.util;

/**
 * Request-scoped bean that holds objects relevant to the current request,
 * including the logged-in user.
 * 
 * @author pbourke
 */
public class UserRequestContext {
    private String email;
    
    public String getUserEmail() {
        return email;
    }
    
    protected void setUserEmail(final String s) {
        email = s;
    }
    
    public boolean isLoggedIn() {
        return getUserEmail() != null;
    }
}
