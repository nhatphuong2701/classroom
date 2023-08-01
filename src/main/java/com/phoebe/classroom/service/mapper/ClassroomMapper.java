package com.phoebe.classroom.service.mapper;

import com.phoebe.classroom.base.mapper.EntityMapper;
import com.phoebe.classroom.entity.ClassroomEntity;
import com.phoebe.classroom.service.model.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClassroomMapper extends EntityMapper<ClassroomEntity, Classroom> {

}
