package com.zxb.admin.service.impl;

import com.zxb.admin.dao.MenuRepository;
import com.zxb.admin.dao.RoleMenuRelRepository;
import com.zxb.admin.entity.Menu;
import com.zxb.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单服务实现
 *
 * @author zjx
 * @date 2020/7/10 0010 15:01
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    /**
     * 查询所有菜单
     *
     * @param
     * @return {@link List<Menu>}
     * @author zjx
     * @date 2020/07/16 13:11
     */
    @Override
    public List<Menu> queryAll() {
        return menuRepository.findAll();
    }

    /**
     * 通过父id查询
     *
     * @param parentId
     * @return {@link List< Menu>}
     * @author zjx
     * @date 2020/10/27 12:25
     */
    @Override
    public List<Menu> findAllByParentId(Integer parentId) {
        return menuRepository.findAllByParentId(parentId);
    }

}
