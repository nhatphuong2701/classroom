package com.phoebe.classroom.base.rest.model;

import com.phoebe.classroom.base.exception.ErrorMessage;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {

    @NotBlank(message = "USERNAME_BLANK_OR_NULL")
    private String userName;

    @NotBlank(message = "PASSWORD_BLANK_OR_NULL")
    private String password;
}
