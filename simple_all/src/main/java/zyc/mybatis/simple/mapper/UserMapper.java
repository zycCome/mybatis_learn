package zyc.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import zyc.mybatis.simple.model.SysRole;
import zyc.mybatis.simple.model.SysUser;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 通过 id 查询用户
     *
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 通过 id 查询用户
     *
     * @param id
     * @return
     */
    SysUser selectById(String id);


    /**
     * 查询全部用户
     *
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 查询全部用户.重载方法
     *
     * @return
     */
    List<SysUser> selectAll(RowBounds rowBounds);


    /**
     * 根据用户 id 获取角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);


    /**
     * 插入一个用户
     * @param sysUser
     * @return  int表示的是影响的行数
     */
    int insert(SysUser sysUser);


    /**
     * 插入一个用户，带有id。
     * 使用 useGeneratedKeys 方式
     * @param sysUser
     * @return  int表示的是影响的行数
     */
    int insert2(SysUser sysUser);

    /**
     * selectkey方式
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);

    /**
     * 根据主键更新
     *
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 通过主键删除
     *
     * @param sysUser
     * @return
     */
    int deleteById(SysUser sysUser);


    /**
     * 根据用户 id 和 角色的 enabled 状态获取用户的角色
     *
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId")Long userId, @Param("enabled")Integer enabled);


    /**
     * 在where中使用if
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 根据主键更新
     * 子啊update时使用if
     *
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    /**
     * 动态插入
     * @param sysUser
     * @return
     */
    int insertSelective(SysUser sysUser);


    /**
     * 根据用户 id 或用户名查询
     * choose标签
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);

    /**
     * 根据用户 id 集合查询
     * foreach用法
     * @param idList
     * @return
     */
    List<SysUser> selectByIdList(List<Long> idList);


    /**
     * 批量插入用户信息
     *
     * @param userList
     * @return
     */
    int insertList(List<SysUser> userList);


    /**
     * 通过 Map 更新列
     *
     * @param map
     * @return
     */
    int updateByMap(@Param("m1") Map<String, Object> map);


    /**
     * 根据用户 id 获取用户信息和用户的角色信息
     *
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById(Long id);

    SysUser selectUserAndRoleById2(Long id);

    /**
     * 根据用户 id 获取用户信息和用户的角色信息，嵌套查询方式
     *
     * @param id
     * @return
     */
    SysUser selectUserAndRoleByIdSelect(Long id);


    /**
     * 获取所有的用户以及对应的所有角色
     *
     * @return
     */
    List<SysUser> selectAllUserAndRoles();

    /**
     * 通过嵌套查询获取指定用户的信息，以及用户的角色和权限信息
     *
     * @param id
     * @return
     */
    SysUser selectAllUserAndRolesSelect(Long id);

}
