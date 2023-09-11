package com.phoebe.classroom.base.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.phoebe.classroom.base.exception.AuthorizationException;
import com.phoebe.classroom.base.exception.ErrorMessage;
import com.phoebe.classroom.base.exception.InputValidationException;
import com.phoebe.classroom.base.rest.model.JwtRequest;
import com.phoebe.classroom.base.security.service.AuthenticationService;
import com.phoebe.classroom.config.AppConfigService;
import com.phoebe.classroom.entity.UserRole;
import com.phoebe.classroom.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.management.relation.Role;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.UUID;

@Stateless
public class JwtUtils {
    private static final String USERNAME = "username";
    private static final String BEARER = "Bearer ";
    private static final String USERID = "id";
    private static final String ROLE = "ROLE";

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private UserService userService;

    public String generateToken(JwtRequest jwtRequest) throws AuthorizationException, InputValidationException {
        if (authenticationService.checkAuthentication(jwtRequest)) {
            throw new InputValidationException(ErrorMessage.KEY_USERNAME_PASSWORD_INVALID, ErrorMessage.USERNAME_PASSWORD_INVALID);
        }

        String token = null;
        String secretKey = AppConfigService.getSecretKey();
        String issuer = AppConfigService.getIssuer();
        int timeToLive = AppConfigService.getTimeToLive();
        Long userId = userService.getByUsername(jwtRequest.getUserName()).getId();
        UserRole role = userService.getByUsername(jwtRequest.getUserName()).getRole();
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            token = JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withJWTId(UUID.randomUUID().toString())
                    .withClaim(USERID, userId)
                    .withClaim(USERNAME, jwtRequest.getUserName())
                    .withClaim(ROLE, String.valueOf(role))
                    .withExpiresAt(new Date(System.currentTimeMillis() + timeToLive))
                    .sign(algorithm);
        } catch(JWTCreationException e) {
            throw new AuthorizationException(Response.Status.UNAUTHORIZED
                    , ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS);
        }
        return token;
    }

    private Long getUserIdFromToken(String authorization) {
        String token = authorization.substring(BEARER.length()).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        return Long.valueOf(decodedJWT.getClaim(USERID).asString());
    }

    private UserRole getRoleFromToken(String authorization) {
        String token = authorization.substring(BEARER.length()).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        return UserRole.valueOf(decodedJWT.getClaim(ROLE).asString());
    }
}
