package com.phoebe.classroom.base.rest.model;

import com.phoebe.classroom.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    private String userName;
    private UserRole role;
    private String type = "Bearer";

    public JwtResponse(String token, String userName, UserRole role) {
        this.token = token;
        this.userName = userName;
        this.role = role;
    }
}
