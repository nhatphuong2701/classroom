package com.phoebe.classroom.service.model;

import com.phoebe.classroom.entity.Gender;
import com.phoebe.classroom.entity.UserRole;
import lombok.*;


import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;

    @NotBlank
    private String firstName;
    private String lastName;

    private String email;

    @NotBlank
    private String username;
    private String password;
    private Gender gender;
    private UserRole role;

    private LocalDate dateOfBirth;
    private String avatar;
}
