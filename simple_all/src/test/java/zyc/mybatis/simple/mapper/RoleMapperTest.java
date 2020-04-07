package zyc.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import zyc.mybatis.simple.model.SysRole;

import java.util.List;

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用 selectById 方法，查询 id = 1 的角色
            SysRole role = roleMapper.selectById(1l);
            //role 不为空
            Assert.assertNotNull(role);
            //roleName = 管理员
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectById2(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用 selectById 方法，查询 id = 1 的角色
//            SysRole role = roleMapper.selectById2(1l);
            SysRole role = roleMapper.selectById3(1l);
            //role 不为空
            Assert.assertNotNull(role);
            //roleName = 管理员
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roles = roleMapper.selectAll();
            //roles 不为空
            Assert.assertNotNull(roles);

            Assert.assertTrue(roles.size()>0);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole newRole = new SysRole();
            newRole.setCreateBy(23l);
            newRole.setEnabled(1);
            newRole.setRoleName("测试角色");
//            int result = roleMapper.insert(newRole);
            int result = roleMapper.insert3(newRole);
            Assert.assertTrue(result == 1);

        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.rollback();
            sqlSession.close();
        }
    }

}
