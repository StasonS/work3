package com.stasiamba.dao;

import java.util.List;

public interface GenericDAO<T, ID> {

    void create(T t);

    T read(ID id);

    void update(T t);

    void delete(T t);

    List<T> getAll();

    public Class<T> getType();
}
