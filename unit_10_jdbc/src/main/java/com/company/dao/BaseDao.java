package com.company.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<Entity> {

    List<Entity> read() throws SQLException;

    Entity read(int id) throws SQLException;

}
