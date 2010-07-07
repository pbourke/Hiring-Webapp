package com.pb.hiring.controllers;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pb.hiring.model.User;
import com.pb.hiring.service.ModelQueryHelper;

@Controller
public class UsersController {
    @Autowired
    private ModelQueryHelper modelQueryHelper;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    @RequestMapping(method=RequestMethod.GET, value="/users")
    public String listUsers(final ModelMap modelMap) {
        modelMap.put("users", modelQueryHelper.allUsers().list());
        return "users/list";
    }
    
    @Transactional
    @RequestMapping(method=RequestMethod.POST, value="/users")
    public String addUser(final User user) {
        sessionFactory.getCurrentSession().save(user);
        return "redirect:/app/users";
    }
}
