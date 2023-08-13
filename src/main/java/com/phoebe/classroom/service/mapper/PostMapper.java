package com.phoebe.classroom.service.mapper;

import com.phoebe.classroom.base.mapper.EntityMapper;
import com.phoebe.classroom.entity.PostEntity;
import com.phoebe.classroom.service.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi", uses = CommentMapper.class)
public interface PostMapper extends EntityMapper<PostEntity, Post> {
    @Mapping(target = "classroomId", source = "classroom.id")
    @Mapping(target = "userId", source = "user.id")
    @Override
    Post toDto(PostEntity entity);
}
