package com.phoebe.classroom.service;

import com.phoebe.classroom.base.exception.ErrorMessage;
import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.dao.CommentDAO;
import com.phoebe.classroom.entity.CommentEntity;
import com.phoebe.classroom.service.mapper.CommentMapper;
import com.phoebe.classroom.service.model.Comment;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CommentService {
    @Inject
    private CommentDAO commentDAO;

    @Inject
    private CommentMapper commentMapper;

    public List<Comment> getAllComments() {
        return commentMapper.toDtoList(commentDAO.findAll());
    }

    public Comment getById(Long id) throws ResourceNotFoundException {
        CommentEntity commentEntity = commentDAO.findById(id).orElseThrow(()
                -> new ResourceNotFoundException(ErrorMessage.COMMENT_NOT_FOUND, ErrorMessage.KEY_COMMENT_NOT_FOUND));
        return commentMapper.toDto(commentEntity);
    }
}
