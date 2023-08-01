package com.phoebe.classroom.service.mapper;

import com.phoebe.classroom.base.mapper.EntityMapper;
import com.phoebe.classroom.entity.CourseEntity;
import com.phoebe.classroom.service.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper extends EntityMapper<CourseEntity, Course> {

}
