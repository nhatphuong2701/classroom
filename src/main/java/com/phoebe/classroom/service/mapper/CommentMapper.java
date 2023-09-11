package com.phoebe.classroom.service.mapper;

import com.phoebe.classroom.base.mapper.EntityMapper;
import com.phoebe.classroom.entity.CommentEntity;
import com.phoebe.classroom.service.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentMapper extends EntityMapper<CommentEntity, Comment> {
    @Mapping(target = "postId", source = "post.id")
    @Mapping(target = "userId", source = "user.id")
    @Override
    Comment toDto(CommentEntity entity);
}
