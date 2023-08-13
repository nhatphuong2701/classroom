package com.phoebe.classroom.dao;

import com.phoebe.classroom.base.dao.BaseDAO;
import com.phoebe.classroom.entity.CommentEntity;
import com.phoebe.classroom.entity.PostEntity;

import javax.ejb.Stateless;
import javax.persistence.criteria.*;
import java.util.List;

@Stateless
public class CommentDAO extends BaseDAO<CommentEntity> {
    public CommentDAO() {
        super(CommentEntity.class);
    }

    @Override
    public List<CommentEntity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<CommentEntity> cq = cb.createQuery(CommentEntity.class);
        Root<CommentEntity> root = cq.from(CommentEntity.class);
        Fetch<CommentEntity, PostEntity> postFetch = root.fetch("post", JoinType.LEFT);
        postFetch.fetch("user", JoinType.LEFT);
        cq.select(root);

        return em.createQuery(cq).getResultList();
    }
}
