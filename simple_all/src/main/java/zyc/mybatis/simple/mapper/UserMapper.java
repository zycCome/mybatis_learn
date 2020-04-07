package zyc.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import zyc.mybatis.simple.model.SysRole;
import zyc.mybatis.simple.model.SysUser;

import java.util.List;

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

}
