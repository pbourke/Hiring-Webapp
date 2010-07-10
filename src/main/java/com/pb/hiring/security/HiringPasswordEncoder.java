package com.pb.hiring.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.util.DigestUtils;

public class HiringPasswordEncoder implements PasswordEncoder {

    private Log logger = LogFactory.getLog( getClass() );
    
    @Override
    public String encodePassword(String plaintextPassword, Object salt)
    throws DataAccessException {
        return DigestUtils.md5DigestAsHex( plaintextPassword.getBytes() );
    }

    @Override
    public boolean isPasswordValid(String encodedPassword, String plaintextPassword, Object salt)
    throws DataAccessException {
        // todo: add a salt string
        return encodedPassword.equals( DigestUtils.md5DigestAsHex(plaintextPassword.getBytes()) );
    }
}
