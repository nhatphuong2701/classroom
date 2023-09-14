package com.phoebe.classroom.base.security.rest;

import com.phoebe.classroom.base.exception.AuthorizationException;
import com.phoebe.classroom.base.exception.InputValidationException;
import com.phoebe.classroom.base.rest.model.JwtRequest;
import com.phoebe.classroom.base.rest.model.JwtResponse;
import com.phoebe.classroom.base.security.JwtUtils;
import com.phoebe.classroom.base.security.service.AuthenticationService;
import com.phoebe.classroom.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthResource {
    @Inject
    private JwtUtils jwtUtils;

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getJwtResponse(JwtRequest jwtRequest) throws AuthorizationException, InputValidationException {
        JwtResponse jwtResponse =jwtUtils.generateJwtResponse(jwtRequest);
        return Response.ok(jwtResponse).build();
    }
}
