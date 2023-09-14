package com.phoebe.classroom.base.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.phoebe.classroom.base.exception.AuthorizationException;
import com.phoebe.classroom.base.exception.ErrorMessage;
import com.phoebe.classroom.base.exception.InputValidationException;
import com.phoebe.classroom.base.rest.model.JwtRequest;
import com.phoebe.classroom.base.rest.model.JwtResponse;
import com.phoebe.classroom.base.security.service.AuthenticationService;
import com.phoebe.classroom.config.AppConfigService;
import com.phoebe.classroom.entity.UserEntity;
import com.phoebe.classroom.entity.UserRole;
import com.phoebe.classroom.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Stateless
public class JwtUtils {
    private static final String USERNAME = "username";
    private static final String BEARER = "Bearer ";
    private static final String USERID = "id";
    private static final String ROLE = "ROLE";
    private static final Validator validator =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();

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
        Long userId = userService.getByUsername(jwtRequest.getUsername()).getId();
        UserRole role = userService.getByUsername(jwtRequest.getUsername()).getRole();
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            token = JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withJWTId(UUID.randomUUID().toString())
                    .withClaim(USERID, userId)
                    .withClaim(USERNAME, jwtRequest.getUsername())
                    .withClaim(ROLE, String.valueOf(role))
                    .withExpiresAt(new Date(System.currentTimeMillis() + timeToLive))
                    .sign(algorithm);
        } catch(JWTCreationException e) {
            throw new AuthorizationException(Response.Status.UNAUTHORIZED
                    , ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS);
        }
        return token;
    }

    private void validateJwtToken(String token) throws AuthorizationException {
        if(token == null) {
            throw new AuthorizationException(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS);
        }
        try {
            String secretKey =AppConfigService.getSecretKey();
            String issuer =AppConfigService.getIssuer();
            Algorithm algorithm =Algorithm.HMAC512(secretKey);
            JWTVerifier verifier =JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw  new AuthorizationException(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS);
        }
    }

    public JwtResponse generateJwtResponse(JwtRequest jwtRequest) throws AuthorizationException, InputValidationException {
        verifyJwtRequest(jwtRequest);
        String token = generateToken(jwtRequest);
        String userName = jwtRequest.getUsername();
        UserEntity user =userService.getByUsername(userName);

        UserRole role = user.getRole();
        return new JwtResponse(token, userName, role);
    }

    public Long getUserIdFromToken(String authorization) throws AuthorizationException {
        String token = authorization.substring(BEARER.length()).trim();
        validateJwtToken(token);
        DecodedJWT decodedJWT = JWT.decode(token);
        Long decodedId = decodedJWT.getClaim(USERID).asLong();
        return decodedId;
    }

    private UserRole getRoleFromToken(String authorization) {
        String token = authorization.substring(BEARER.length()).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        return UserRole.valueOf(decodedJWT.getClaim(ROLE).asString());
    }

    private void verifyJwtRequest(JwtRequest jwtRequest) {
        Set<ConstraintViolation<JwtRequest>> violations = validator.validate(jwtRequest);
        if(CollectionUtils.isNotEmpty(violations)) {
            throw new ConstraintViolationException(violations);
        }
    }

    public void verifyUserIsTeacher(String authorization) throws AuthorizationException {
        String token = authorization.substring(BEARER.length());
        validateJwtToken(token);
        UserRole role = getRoleFromToken(authorization);
        if (!role.equals(UserRole.ROLE_TEACHER)) {
            throw new AuthorizationException(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS);
        }
    }
}
