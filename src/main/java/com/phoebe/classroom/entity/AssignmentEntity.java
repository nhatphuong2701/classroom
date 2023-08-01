package com.phoebe.classroom.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "assignment")
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDateTime dueTime;
    private String attachment;
    private String instruction;
    private Long points;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassroomEntity classroom;
}
