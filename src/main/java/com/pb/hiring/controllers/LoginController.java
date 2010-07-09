package com.pb.hiring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pb.hiring.controllers.util.UserRequestContext;
import com.pb.hiring.model.User;
import com.pb.hiring.service.ModelQueryHelper;

/**
 * Handles login operations and information display.
 * 
 * @author pbourke
 */
@Controller
public class LoginController {
    @Autowired
    private UserRequestContext userRequestContext;
    
    @Autowired
    private ModelQueryHelper modelQueryHelper;
    
    /**
     * Renders a partial for the logged-in user status.
     */
    @RequestMapping(method=RequestMethod.GET, value="/login/status")
    public String getStatusPartial(final ModelMap modelMap) {
        modelMap.put("userRequestContext", userRequestContext);
        return "login/_status";
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/login")
    public String getLoginPage() {
        return "login/login";
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/login")
    public String login(@RequestParam("email") final String email, @RequestParam("password") final String password) {
        final User user = (User) modelQueryHelper.userByEmail(email).uniqueResult();
        if ( null == user || user.passwordMatches(password) ) {
            return getLoginPage();
        }
        return "redirect:/app/users";
    }
}
