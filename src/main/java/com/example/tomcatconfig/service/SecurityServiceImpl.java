package com.example.tomcatconfig.service;

import com.example.tomcatconfig.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityServiceImpl(AuthenticationManager authenticationManager,UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (userDetails instanceof UserDetails) ? ((UserDetails) userDetails).getUsername() : null;
    }

    public void closeSession(HttpServletRequest request,HttpServletResponse response, SecurityContextLogoutHandler logoutHandler){
        logoutHandler.logout(request, response, currentAuthentication());
    }

    public boolean authenticationIsNotAnonymous(){
        return !(currentAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public Authentication currentAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public void autoLogin(String login, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        if (token.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }
}
