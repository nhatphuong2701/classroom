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

import static com.phoebe.classroom.base.exception.ErrorMessage.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonbDateFormat("yyyy-MM-dd''HH:mm:ss")
    private LocalDateTime postTime;

    @NotBlank(message = POST_CONTENT_NULL_OR_BLANK)
    @Size(max = 2000, message = "POST_CONTENT_LENGTH_CONSTRAINT")
    private String content;

    private String attachment;

    @Size(max = 250, message = "POST_TITLE_LENGTH_CONSTRAINT")
    private String title;

    private Long classroomId;

    private Long userId;
}
