package com.phoebe.classroom.service;

import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.dao.PostDAO;
import com.phoebe.classroom.entity.PostEntity;
import com.phoebe.classroom.service.mapper.PostMapper;
import com.phoebe.classroom.service.model.Post;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class PostServiceTest {
    @InjectMocks
    private PostService postService;

    @Mock
    private PostDAO postDAO;

    @Mock
    private PostMapper postMapper;

    @Test
    void getAll_Successfully_ReturnListOf2Posts() {
        List<PostEntity> validPostList = createValidPostList();
        List<Post> validPostDTOList = createValidPostDTOList();

        when(postDAO.findAll()).thenReturn(validPostList);
        when(postMapper.toDtoList(validPostList)).thenReturn(validPostDTOList);

        assertEquals(validPostDTOList.size(), postService.getAllPosts().size());
    }

    @Test
    void getById_IdExisting_ReturnPostDTO() throws ResourceNotFoundException {
        PostEntity validPost = createValidPost1();
        Post validPostDTO = createValidPostDTO1();

        when(postDAO.findById(1L)).thenReturn(Optional.ofNullable(validPost));
        when(postMapper.toDto(validPost)).thenReturn(validPostDTO);

        assertEquals(validPostDTO.getId(), postService.getById(1L).getId());
        assertEquals(validPostDTO.getTitle(), postService.getById(1L).getTitle());
        assertEquals(validPostDTO.getContent(), postService.getById(1L).getContent());
        assertEquals(validPostDTO.getPostTime(),postService.getById(1L).getPostTime());
    }

    PostEntity createValidPost1() {
        return PostEntity.builder()
                .id(1L)
                .postTime(LocalDateTime.parse("2023-08-01T12:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .title("Post 1")
                .content("Content 1")
                .build();
    }

    PostEntity createValidPost2() {
        return PostEntity.builder()
                .id(2L)
                .postTime(LocalDateTime.parse("2023-08-02T13:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .title("Post 2")
                .content("Content 2")
                .build();
    }

    Post createValidPostDTO1() {
        return Post.builder()
                .id(1L)
                .postTime(LocalDateTime.parse("2023-08-01T12:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .title("Post 1")
                .content("Content 1")
                .build();
    }

    Post createValidPostDTO2() {
        return Post.builder()
                .id(2L)
                .postTime(LocalDateTime.parse("2023-08-02T13:00:00", DateTimeFormatter.ISO_DATE_TIME))
                .title("Post 2")
                .content("Content 2")
                .build();
    }

    List<PostEntity> createValidPostList() {
        return Arrays.asList(createValidPost1(), createValidPost2());
    }

    List<Post> createValidPostDTOList() {
        return Arrays.asList(createValidPostDTO1(), createValidPostDTO2());
    }
}