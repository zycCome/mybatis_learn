package zyc.mybatis.simple.model;

import lombok.Data;

/**
 * 角色权限关联表
 */
@Data
public class SysRolePrivilege {
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 权限ID
	 */
	private Long privilegeId;
}
