package com.phoebe.classroom.service.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.json.bind.annotation.JsonbDateFormat;
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
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonbDateFormat("yyyy-MM-dd''HH:mm:ss")
    private LocalDateTime commentTime;

    @NotBlank
    @Size(max = 2000)
    private String content;

    private String attachment;
    private Long postId;
    private Long userId;
}
