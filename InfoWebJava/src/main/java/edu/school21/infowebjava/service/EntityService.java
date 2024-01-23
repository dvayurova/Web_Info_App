package edu.school21.infowebjava.service;


import edu.school21.infowebjava.models.EntityInterface;

import java.util.List;

public interface EntityService<E, T> {
    public List<? extends EntityInterface> getAll();
    public E findById(T id);
    public E add(E e);
    public E update(E e);
    public void delete(T id);

    List<String> columnNames();
    public String tableName();

}


