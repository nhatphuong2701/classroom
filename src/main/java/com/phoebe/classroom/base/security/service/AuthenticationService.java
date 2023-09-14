package com.phoebe.classroom.base.security.service;

import com.phoebe.classroom.base.exception.AuthorizationException;
import com.phoebe.classroom.base.rest.model.JwtRequest;
import com.phoebe.classroom.entity.UserEntity;
import com.phoebe.classroom.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthenticationService {
    @Inject
    private UserService userService;
    public boolean checkAuthentication(JwtRequest jwtRequest) throws AuthorizationException {
        UserEntity user = userService.getByUsername(jwtRequest.getUsername());
        return BCrypt.checkpw(jwtRequest.getPassword(), user.getPassword());
    }
}
