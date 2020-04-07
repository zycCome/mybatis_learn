package zyc.mybatis.simple.mapper;

import org.apache.ibatis.jdbc.SQL;

public class RoleMapperProvider {

    public String selectById(final Long id){
        return new SQL(){
            {
                SELECT("id,role_name, enabled, create_by, create_time");
                FROM("sys_role");
                WHERE("id = #{id}");
            }
        }.toString();
    }

}
