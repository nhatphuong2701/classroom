package com.phoebe.classroom.service;

import com.phoebe.classroom.base.exception.ErrorMessage;
import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.dao.PostDAO;
import com.phoebe.classroom.entity.PostEntity;
import com.phoebe.classroom.service.mapper.PostMapper;
import com.phoebe.classroom.service.model.Post;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class PostService {
    @Inject
    private PostDAO postDAO;

    @Inject
    private PostMapper postMapper;

    public List<Post> getAllPost() {
        return postMapper.toDtoList(postDAO.findAll());
    }

    public Post getById(Long id) throws ResourceNotFoundException {
        PostEntity postEntity = postDAO.findById(id)
                .orElseThrow(()
                        -> new ResourceNotFoundException(ErrorMessage.KEY_POST_NOT_FOUND, ErrorMessage.POST_NOT_FOUND));
        return postMapper.toDto(postEntity);
    }
}
