package com.phoebe.classroom.service.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long id;
    private LocalDateTime postTime;

    @NotBlank
    @Size(max = 2000)
    private String content;

    private String attachment;
    private String title;
    private Long classroomId;
    private Long userId;
}
