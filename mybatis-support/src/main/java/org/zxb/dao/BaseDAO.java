package org.zxb.dao;

public interface BaseDAO<T> {

    public T selectByPrimary(String primary);

}
