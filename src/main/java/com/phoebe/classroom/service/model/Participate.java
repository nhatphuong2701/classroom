package com.phoebe.classroom.service.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participate {
    private Long id;
    private Long userId;
    private Long classroomId;
}
