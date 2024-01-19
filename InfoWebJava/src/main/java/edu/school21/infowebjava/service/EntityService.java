package edu.school21.infowebjava.service;


import edu.school21.infowebjava.models.EntityInterface;

import java.util.List;

public interface EntityService {
    public List<? extends EntityInterface> getAll();

    List<String> columnNames();
    public String tableName();

}


