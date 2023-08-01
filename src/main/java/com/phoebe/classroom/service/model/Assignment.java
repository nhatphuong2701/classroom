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
public class Assignment {
    private Long id;

    @NotBlank
    private String name;

    private LocalDateTime dueTime;
    private String attachment;
    private String instruction;
    private Long points;
    private Long classroomId;
}
