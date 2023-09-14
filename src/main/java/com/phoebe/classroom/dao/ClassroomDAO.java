package com.phoebe.classroom.dao;

import com.phoebe.classroom.base.dao.BaseDAO;
import com.phoebe.classroom.entity.ClassroomEntity;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
public class ClassroomDAO extends BaseDAO<ClassroomEntity> {
    public ClassroomDAO() {
        super(ClassroomEntity.class);
    }

    public Optional<List<ClassroomEntity>> getClassroomsByUserId(Long userId) {
        TypedQuery<ClassroomEntity> query = em.createQuery(
                "SELECT c FROM ClassroomEntity c " +
                        "JOIN ParticipateEntity p ON c.id = p.classroom.id " +
                        "JOIN p.user u " +
                        "WHERE u.id = :userId", ClassroomEntity.class);

        query.setParameter("userId", userId);

        if (CollectionUtils.isNotEmpty(query.getResultList())) {
            return Optional.of(query.getResultList());
        } else {
            return Optional.empty();
        }
    }
}
