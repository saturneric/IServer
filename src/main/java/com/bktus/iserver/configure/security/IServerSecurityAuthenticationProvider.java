package com.bktus.iserver.configure.security;

import com.bktus.iserver.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class IServerSecurityAuthenticationProvider implements AuthenticationProvider {
    @Resource
    private IServerPasswordEncoder encoder;

    @Resource
    private UserDetailsService detailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        password = encoder.encode(password);

        UserDetails details =  detailsService.loadUserByUsername(username);

        if(!details.getPassword().equals(password)){
            throw new BadCredentialsException("Password IS Uncorrected");
        }

        return new UsernamePasswordAuthenticationToken(details, password, details.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        if(aClass.equals(UsernamePasswordAuthenticationToken.class)) return true;
        else return false;
    }
}
