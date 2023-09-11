package com.phoebe.classroom.service;

import com.phoebe.classroom.base.exception.AuthorizationException;
import com.phoebe.classroom.base.exception.ErrorMessage;
import com.phoebe.classroom.dao.UserDAO;
import com.phoebe.classroom.entity.UserEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
public class UserService {
    @Inject
    private UserDAO userDAO;

    public UserEntity getByUsername(String username) throws AuthorizationException {
        return userDAO.findByUserName(username).orElseThrow(
                ()-> new AuthorizationException(Response.Status.UNAUTHORIZED
                        , ErrorMessage.KEY_USERNAME_NULL_OR_BLANK, ErrorMessage.USERNAME_NULL_OR_BLANK));
    }
}
