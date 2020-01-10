package org.zxb.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDAO<T> {

    List<T> select(@Param(value = "obj")T user);

    Integer insert(@Param(value = "obj")T user);

    Integer delete(@Param(value = "obj")T user);

    Integer update(@Param(value = "obj") T user, @Param(value = "whe") T where);
}
