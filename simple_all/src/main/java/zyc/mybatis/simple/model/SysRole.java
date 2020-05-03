package zyc.mybatis.simple.model;


import lombok.Data;
import zyc.mybatis.simple.type.Enabled;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 6320941908222932112L;
    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 有效标志
     */
    private Enabled enabled;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户信息
     */
    private SysUser user;

    /**
     * 角色包含的权限列表
     */
    List<SysPrivilege> privilegeList;

}
