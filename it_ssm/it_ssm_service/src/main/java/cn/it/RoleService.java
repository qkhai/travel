package cn.it;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {

    List<Role> findAll() throws Exception;

    void save(Role role);

    List<Role> findOtherRole(String id);

    Role findRoleById(String roleId);

    void addPermissionToRole(String roleId, String[] ids);
}
