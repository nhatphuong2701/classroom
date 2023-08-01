package com.phoebe.classroom.base.dao;

import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public abstract class BaseDAO<E> {
    @PersistenceContext(name = "classroom")
    private EntityManager em;

    private Class<E> entityClass;

    public List<E> findAll() {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e",entityClass).getResultList();
    }

    public Optional<E> findById(Long id) {
        List<E> entityList = em
                .createQuery("SELECT e FROM " + entityClass.getSimpleName() + " WHERE e.id = :id",entityClass)
                .setParameter("id", id)
                .getResultList();
        return entityList.isEmpty() ? Optional.empty() :Optional.of(entityList.get(0));
    }
}
