package wang.jinggo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.dao.PermissionDao;
import wang.jinggo.domain.Permission;
import wang.jinggo.service.PermissionService;

import java.util.List;

/**
 * 权限接口实现
 * @author wangyj
 * @description
 * @create 2018-09-25 14:30
 **/
@Slf4j
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findByTypeAndStatusOrderBySortOrder(Integer type, Integer status) {
        return permissionDao.findByTypeAndStatusOrderBySortOrder(type,status);
    }

    @Override
    public XbootBaseDao<Permission, String> getRepository() {
        return permissionDao;
    }
}