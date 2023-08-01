package com.phoebe.classroom.service.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classroom {
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    private LocalDate startDate;
    private LocalDate endDate;

    @NotBlank
    private Long academicYear;

    private Long courseId;
}
