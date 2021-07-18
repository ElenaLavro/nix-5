package com.company.dao;

import com.company.data.BaseData;

import java.util.Date;
import java.util.List;

public interface BaseDao<DATA extends BaseData> {
    void create(DATA data);
    void update(DATA data);
    DATA read(int id);
    List<DATA> readAll();
    void delete(int id);
}
