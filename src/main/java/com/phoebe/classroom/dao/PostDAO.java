package com.phoebe.classroom.dao;

import com.phoebe.classroom.base.dao.BaseDAO;
import com.phoebe.classroom.entity.PostEntity;
import com.phoebe.classroom.entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Stateless
public class PostDAO extends BaseDAO<PostEntity> {
    public PostDAO() {
        super(PostEntity.class);
    }

    @Override
    public List<PostEntity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PostEntity> cq = cb.createQuery(PostEntity.class);
        Root<PostEntity> root = cq.from(PostEntity.class);
        root.fetch("user", JoinType.LEFT);
        root.fetch("classroom", JoinType.LEFT);
        cq.select(root);
        return em.createQuery(cq).getResultList();
    }
}
