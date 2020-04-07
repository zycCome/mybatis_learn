package zyc.mybatis.simple.model;


import lombok.Data;

import java.util.Date;

@Data
public class SysRole {

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
    private Integer enabled;
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

}
