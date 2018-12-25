package cn.it.dao;

import cn.it.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {
    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.it.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id not in (select roleid from users_role where userid = #{id})")
    List<Role> findOtherRole(String id);

    @Select("select * from role where id = #{roleId}")
    Role findRoleById(String roleId);

    @Insert("insert into role_permission values(#{id},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("id") String id);
}
