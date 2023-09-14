package com.phoebe.classroom.base.rest.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {

    @NotBlank(message = "USERNAME_BLANK_OR_NULL")
    private String username;

    @NotBlank(message = "PASSWORD_BLANK_OR_NULL")
    private String password;
}
