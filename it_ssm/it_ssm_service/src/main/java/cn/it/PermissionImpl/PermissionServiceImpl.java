package cn.it.PermissionImpl;


import cn.it.Permission;
import cn.it.PermissionService;
import cn.it.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission p)throws Exception{
        permissionDao.save(p);
    }

    @Override
    public List<Permission> findOtherPermission(String id) {
        return permissionDao.findOtherPermission(id);
    }
}
