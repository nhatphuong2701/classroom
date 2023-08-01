package com.phoebe.classroom.entity;

import lombok.*;

import javax.persistence.*;

@Entity()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;
}
