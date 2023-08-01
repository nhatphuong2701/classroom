package com.phoebe.classroom.base.mapper;

import java.util.List;

public interface EntityMapper<E, D> {

    D toDto(E entity);

    List<D> toDtoList(List<E> entityList);
}
