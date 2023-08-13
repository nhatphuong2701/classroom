package com.phoebe.classroom.base.dao;

import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public abstract class BaseDAO<E> {
    @PersistenceContext(name = "classroom")
    protected EntityManager em;

    public BaseDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    private Class<E> entityClass;


    public List<E> findAll() {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<E> cq = cb.createQuery(entityClass);
        Root<E> root = cq.from(entityClass);
        cq.select(root);

        return em.createQuery(cq).getResultList();
    }

    public Optional<E> findById(Long id) {
        return Optional.of(em.find(entityClass, id));
    }
}
