package cn.it;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission p) throws Exception;

    List<Permission> findOtherPermission(String id);
}
