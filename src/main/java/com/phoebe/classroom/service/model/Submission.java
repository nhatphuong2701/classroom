package com.phoebe.classroom.service.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {
    private Long id;
    private String note;
    private String attachment;
    private Long points;
    private Long userId;
    private Long assignmentId;

    @NotBlank
    private LocalDateTime submitTime;
}
