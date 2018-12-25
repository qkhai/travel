package cn.it.RoleImpl;

import cn.it.Role;
import cn.it.RoleService;
import cn.it.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> findAll() throws Exception{
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Role> findOtherRole(String id) {
        return roleDao.findOtherRole(id);
    }

    @Override
    public Role findRoleById(String roleId) {
        return roleDao.findRoleById(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String id : ids) {
            roleDao.addPermissionToRole(roleId,id);
        }
    }
}
