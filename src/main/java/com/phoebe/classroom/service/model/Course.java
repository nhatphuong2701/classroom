package com.phoebe.classroom.service.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private Long id;

    @NotBlank
    private String name;

    @Size(max = 2000)
    private String description;
}
