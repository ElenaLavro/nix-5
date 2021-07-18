package com.company.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<Entity> {
    void create(Entity entity) throws SQLException;

    List<Entity> read() throws SQLException;

    Entity read(int id) throws SQLException;

    void update(Entity entity, int id) throws SQLException;

    void delete(int id);

}
