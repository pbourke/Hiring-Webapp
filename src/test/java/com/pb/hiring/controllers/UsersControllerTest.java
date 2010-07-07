package com.pb.hiring.controllers;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.pb.hiring.model.User;
import com.pb.hiring.service.ModelQueryHelper;

@Transactional
@TransactionConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:WEB-INF/applicationContext.xml", "classpath:WEB-INF/app-beans.xml", "classpath:com/pb/hiring/unit-test-beans.xml"})
public class UsersControllerTest {
    @Autowired(required=true)
    private UsersController usersController;
    
    @Autowired(required=true)
    private ModelQueryHelper modelQueryHelper;
    
    @BeforeClass
    public static void setUpLog4J() {
        BasicConfigurator.configure();
    }

    @Test
    public void testEmptyUsersList() {
        assertTrue( "Users list is empty", modelQueryHelper.allUsers().list().isEmpty() );
        final ModelMap modelMap = new ModelMap();
        assertEquals("users/list", usersController.listUsers(modelMap));
        assertTrue( modelMap.containsKey("users") );
        assertTrue( "ModelMap contains new user command object", modelMap.containsKey("newUser") );
        assertTrue( "newUser is-a User", modelMap.get("newUser") instanceof User);
        
        final List<User> users = (List<User>) modelMap.get("users");
        
        assertTrue( users.isEmpty() );        
    }
    
    @Test
    public void testAddUser() {
        final User user = new User();
        user.setEmail("test@example.com");
        user.setName("Test Person");
        user.setPasswordPlaintext("password");
        
        assertEquals("redirect:/app/users", usersController.addUser(user));
        
        final User u2 = (User) modelQueryHelper.userByEmail("test@example.com").uniqueResult();
        
        assertNotNull( u2 );
        
        assertEquals("test@example.com", u2.getEmail());
        assertEquals("Test Person", u2.getName());
        assertTrue(u2.getPasswordDigest() != null);
        
        assertEquals(1, modelQueryHelper.allUsers().list().size());
    }
    
    @Test(expected=Exception.class)
    public void testAddUserNoEmail() {
        assertTrue( "users list is empty", modelQueryHelper.allUsers().list().isEmpty() );
        final User user = new User();
        user.setName("Test Person");
        user.setPasswordPlaintext("password");

        try {
            usersController.addUser(user);
            fail("Exception expected");
        } finally {
            assertTrue( "users list is empty", modelQueryHelper.allUsers().list().isEmpty() );            
        }
    }

}
