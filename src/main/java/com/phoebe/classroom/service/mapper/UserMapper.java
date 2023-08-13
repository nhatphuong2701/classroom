package com.phoebe.classroom.service.mapper;

import com.phoebe.classroom.base.mapper.EntityMapper;
import com.phoebe.classroom.entity.UserEntity;
import com.phoebe.classroom.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends EntityMapper<UserEntity, User> {
    @Override
    @Mapping(target = "password", ignore = true)
    List<User> toDtoList(List<UserEntity> entityList);
}
