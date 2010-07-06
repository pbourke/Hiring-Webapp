package com.pb.hiring.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class UserTest {
    @Test
    public void testPasswordDigest() {
        final User u = new User();
        assertNull( u.getPasswordDigest() );
        u.setPasswordPlaintext("a password");
        
        assertNotNull( u.getPasswordDigest() );
        assertEquals( 32, u.getPasswordDigest().length() );
        assertEquals( User.computePasswordDigest("a password"), u.getPasswordDigest() );
        System.out.println(u.getPasswordDigest());
    }
}
