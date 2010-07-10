package com.pb.hiring.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.pb.hiring.model.User;
import com.pb.hiring.service.ModelQueryHelper;

public class HiringUserDetailsService implements UserDetailsService {

    @Autowired
    private ModelQueryHelper modelQueryHelper;
    
    private List<GrantedAuthority> grantedAuthorities = Arrays.asList( new GrantedAuthority[]{ new GrantedAuthorityImpl("ROLE_USER") } );
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) 
    throws UsernameNotFoundException, DataAccessException {
        final User user = (User) modelQueryHelper.userByEmail(username).uniqueResult();
        if ( null == user ) {
            throw new UsernameNotFoundException("Unable to find " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordDigest(), true, true, true, true, grantedAuthorities);
    }
}
