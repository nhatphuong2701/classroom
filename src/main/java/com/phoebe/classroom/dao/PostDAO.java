package com.phoebe.classroom.dao;

import com.phoebe.classroom.base.dao.BaseDAO;
import com.phoebe.classroom.entity.PostEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PostDAO extends BaseDAO<PostEntity> {
    @PersistenceContext(name = "classroom")
    private EntityManager em;

}
