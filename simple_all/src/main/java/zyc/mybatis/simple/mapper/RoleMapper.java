package zyc.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import zyc.mybatis.simple.model.SysRole;

import java.util.List;

public interface RoleMapper {

    @Select({"select id,role_name roleName, enabled, create_by createBy, create_time createTime",
            "from sys_role",
            "where id = #{id}"})
    SysRole selectById(Long id);

    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),//注意id=true，表示xml中的id标签
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select id,role_name, enabled, create_by, create_time from sys_role where id = #{id}")
    SysRole selectById2(Long id);

    /**
     * type指定实现类，method指定方法名
     * @param id
     * @return
     */
    @SelectProvider(type = RoleMapperProvider.class,method = "selectById")
    @ResultMap("roleResultMap")//引用了上面的@Results的内容
    SysRole selectById3(Long id);


    @ResultMap("roleResultMap")//引用了上面的@Results的内容
    @Select("select * from sys_role")
    List<SysRole> selectAll();


    /**
     * 最普通的插入，这里id时数据自增的。但插入后，后端获取不到具体的值
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(id, role_name, enabled, create_by, create_time)",
            "values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    int insert(SysRole sysRole);


    /**
     * 使用useGeneratedKeys
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")//keyProperty声明把id设置到哪个bean的字段
    int insert2(SysRole sysRole);

    /**
     * 这里是mysql，所以before=false
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Long.class, before = false)
    int insert3(SysRole sysRole);



}
