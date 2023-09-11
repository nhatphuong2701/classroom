package com.phoebe.classroom.service;

import com.phoebe.classroom.base.exception.ErrorMessage;
import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.dao.ClassroomDAO;
import com.phoebe.classroom.dao.PostDAO;
import com.phoebe.classroom.dao.UserDAO;
import com.phoebe.classroom.entity.ClassroomEntity;
import com.phoebe.classroom.entity.PostEntity;
import com.phoebe.classroom.entity.UserEntity;
import com.phoebe.classroom.service.mapper.PostMapper;
import com.phoebe.classroom.service.model.Post;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Stateless
public class PostService {

    private static final Validator validator =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();
    @Inject
    private PostDAO postDAO;

    @Inject
    private PostMapper postMapper;

    @Inject
    private ClassroomDAO classroomDAO;

    @Inject
    private UserDAO userDAO;

    public List<Post> getAllPost() {
        return postMapper.toDtoList(postDAO.findAll());
    }

    public Post getById(Long id) throws ResourceNotFoundException {
        PostEntity postEntity = postDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        (ErrorMessage.KEY_POST_NOT_FOUND, ErrorMessage.POST_NOT_FOUND));
        return postMapper.toDto(postEntity);
    }

    public PostEntity create(Post post) throws ResourceNotFoundException {
        verifyPost(post);

        ClassroomEntity classroom = classroomDAO.findById(post.getClassroomId())
                .orElseThrow(() -> new ResourceNotFoundException
                        (ErrorMessage.KEY_CLASSROOM_NOT_FOUND, ErrorMessage.CLASSROOM_NOT_FOUND));

        UserEntity user = userDAO.findById(post.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException
                        (ErrorMessage.KEY_USER_NOT_FOUND, ErrorMessage.USER_NOT_FOUND));

        PostEntity newPost = PostEntity.builder()
                .postTime(LocalDateTime.now())
                .title(post.getTitle())
                .classroom(classroom)
                .user(user)
                .content(post.getContent())
                .attachment(post.getAttachment())
                .build();

        return postDAO.create(newPost);
    }

    private void verifyPost(Post post) {
        Set<ConstraintViolation<Post>> violations = validator.validate(post);
        if (CollectionUtils.isNotEmpty(violations)) {
            throw new ConstraintViolationException(violations);
        }
    }
}
