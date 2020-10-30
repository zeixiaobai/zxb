package com.zxb.admin.dao;

import com.zxb.admin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 菜单持久类
 *
 * @author zjx
 * @date 2020/7/10 0010 15:04
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    /**
     *  通过父id查询
     *
     * @param parentId
     * @author zjx
     * @return {@link List< Menu>}
     * @date 2020/10/27 12:25
     */
    List<Menu> findAllByParentId(Integer parentId);

}
