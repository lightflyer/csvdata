package com.mycompany.dao;

import java.util.List;

public interface GenericDao<T> {
/**
 * 
 * @param list 插入的数据
 * @param sql  插入的sql语句
 * @return
 */
boolean save(List<T> list,String sql);
}
