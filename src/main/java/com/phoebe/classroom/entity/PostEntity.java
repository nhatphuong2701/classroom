package com.phoebe.classroom.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime postTime;

    @Column(nullable = false, length = 2000)
    private String content;

    private String attachment;
    private String title;

    @ManyToOne()
    @JoinColumn(name = "classroom_id", nullable = false)
    private ClassroomEntity classroom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> commentList;
}
