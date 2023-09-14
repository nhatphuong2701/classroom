package com.phoebe.classroom.service;

import com.phoebe.classroom.base.exception.ErrorMessage;
import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.dao.ClassroomDAO;
import com.phoebe.classroom.entity.ClassroomEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ClassroomService {
    @Inject
    private ClassroomDAO classroomDAO;

    public List<ClassroomEntity> getByUserId(Long userId) throws ResourceNotFoundException {
        return classroomDAO.getClassroomsByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessage.KEY_CLASSROOM_NOT_FOUND, ErrorMessage.CLASSROOM_NOT_FOUND));
    }
}
