package com.phoebe.classroom.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Long id;

    @NotBlank
    private LocalDateTime commentTime;

    @NotBlank
    @Size(max = 2000)
    private String content;

    private String attachment;
    private Long postId;
}
