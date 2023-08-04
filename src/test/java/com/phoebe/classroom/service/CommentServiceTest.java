package com.phoebe.classroom.service;

import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.dao.CommentDAO;
import com.phoebe.classroom.entity.CommentEntity;
import com.phoebe.classroom.entity.PostEntity;
import com.phoebe.classroom.service.mapper.CommentMapper;
import com.phoebe.classroom.service.model.Comment;
import com.phoebe.classroom.service.model.Post;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith({MockitoExtension.class})
class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentDAO commentDAO;

    @Mock
    private CommentMapper commentMapper;

    @Test
    void getAll_Successfully_ReturnListOf2Comments() {
        List<CommentEntity> validCommentList = createValidCommentList();
        List<Comment> validCommentDTOList = createValidCommentDTOList();

        when(commentDAO.findAll()).thenReturn(validCommentList);
        when(commentMapper.toDtoList(validCommentList)).thenReturn(validCommentDTOList);

        assertEquals(validCommentDTOList.size(), commentService.getAllComments().size());
    }

    @Test
    void getById_ExistingId_ReturnCommentDTO() throws ResourceNotFoundException {
        CommentEntity validComment = createValidComment1();
        Comment validCommentDTO = createValidCommentDTO1();

        when(commentDAO.findById(1L)).thenReturn(Optional.ofNullable(validComment));
        when(commentMapper.toDto(validComment)).thenReturn(validCommentDTO);

        assertEquals(validCommentDTO.getId(), commentService.getById(1L).getId());
        assertEquals(validCommentDTO.getContent(), commentService.getById(1L).getContent());
        assertEquals(validCommentDTO.getCommentTime(), commentService.getById(1L).getCommentTime());
    }

    CommentEntity createValidComment1() {
        return CommentEntity.builder()
                .id(1L)
                .content("Content 1")
                .commentTime(LocalDateTime.parse("2023-08-02T12:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }

    CommentEntity createValidComment2() {
        return CommentEntity.builder()
                .id(2L)
                .content("Content 2")
                .commentTime(LocalDateTime.parse("2023-08-02T13:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }

    Comment createValidCommentDTO1() {
        return Comment.builder()
                .id(1L)
                .content("Content 1")
                .commentTime(LocalDateTime.parse("2023-08-02T12:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }

    Comment createValidCommentDTO2() {
        return Comment.builder()
                .id(2L)
                .content("Content 2")
                .commentTime(LocalDateTime.parse("2023-08-02T13:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }

    List<CommentEntity> createValidCommentList() {
        return Arrays.asList(createValidComment1(), createValidComment2());
    }

    List<Comment> createValidCommentDTOList() {
        return Arrays.asList(createValidCommentDTO1(), createValidCommentDTO2());
    }

    Post createValidPostDTO1() {
        return Post.builder()
                .id(1L)
                .postTime(LocalDateTime.parse("2023-08-01T12:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .title("Post 1")
                .content("Content 1")
                .build();
    }
}