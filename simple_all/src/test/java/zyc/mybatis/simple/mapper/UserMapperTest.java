package zyc.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import zyc.mybatis.simple.mapper.impl.MyMapperProxy;
import zyc.mybatis.simple.model.SysRole;
import zyc.mybatis.simple.model.SysUser;

import java.lang.reflect.Proxy;
import java.util.*;

public class UserMapperTest extends BaseMapperTest {
	
	@Test
	public void testSelectById(){
		//获取 sqlSession
		SqlSession sqlSession = getSqlSession();
		try {
			//获取 UserMapper 接口
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用 selectById 方法，查询 id = 1 的用户
			SysUser user = userMapper.selectById(1l);
			SysUser user2 = userMapper.selectById("2");
			//user 不为空
			Assert.assertNotNull(user);
			//userName = admin
			Assert.assertEquals("admin", user.getUserName());
		} finally {
			//不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}
	
	@Test
	public void testSelectAll(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用 selectAll 方法查询所有用户
			List<SysUser> userList = userMapper.selectAll();
			//结果不为空
			Assert.assertNotNull(userList);
			//用户数量大于 0 个
			Assert.assertTrue(userList.size() > 0);
		} finally {
			//不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}

	@Test
	public void testSelectRolesByUserId(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用 selectRolesByUserId 方法查询用户的角色
			List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
			//结果不为空
			Assert.assertNotNull(roleList);
			//角色数量大于 0 个
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			//不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}


	@Test
	public void testInsert(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个 user 对象
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			//正常情况下应该读入一张图片存到 byte 数组中
//			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
			//将新建的对象插入数据库中，特别注意，这里的返回值 result 是执行的 SQL 影响的行数
			int result = userMapper.insert(user);
			//只插入 1 条数据
			Assert.assertEquals(1, result);
			//id 为 null，我们没有给 id 赋值，并且没有配置回写 id 的值
			Assert.assertNull(user.getId());
		} finally {
			//为了不影响数据库中的数据导致其他测试失败，这里选择回滚
			//由于默认的 sqlSessionFactory.openSession() 是不自动提交的，
			//因此不手动执行 commit 也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}


	@Test
	public void testInsert2(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//创建一个 user 对象
			SysUser user = new SysUser();
			user.setUserName("test1");
			user.setUserPassword("123456");
			user.setUserEmail("test@mybatis.tk");
			user.setUserInfo("test info");
			user.setHeadImg(new byte[]{1,2,3});
			user.setCreateTime(new Date());
//			int result = userMapper.insert2(user);
			int result = userMapper.insert3(user);
			//只插入 1 条数据
			Assert.assertEquals(1, result);
			//因为 id 回写，所以 id 不为 null
			Assert.assertNotNull(user.getId());

		} finally {
//			sqlSession.commit();
			//不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}


	@Test
	public void testUpdateById(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//从数据库查询 1 个 user 对象
			SysUser user = userMapper.selectById(1L);
			//当前 userName 为 admin
			Assert.assertEquals("admin", user.getUserName());
			//修改用户名
			user.setUserName("admin_test");
			//修改邮箱
			user.setUserEmail("test@mybatis.tk");
			//更新数据，特别注意，这里的返回值 result 是执行的 SQL 影响的行数
			int result = userMapper.updateById(user);
			//只更新 1 条数据
			Assert.assertEquals(1, result);
			//根据当前 id 查询修改后的数据
			user = userMapper.selectById(1L);
			//修改后的名字 admin_test
			Assert.assertEquals("admin_test", user.getUserName());
		} finally {
			//为了不影响数据库中的数据导致其他测试失败，这里选择回滚
			//由于默认的 sqlSessionFactory.openSession() 是不自动提交的，
			//因此不手动执行 commit 也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}

	@Test
	public void testDeleteById(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//从数据库查询 1 个 user 对象，根据 id = 1 查询
			SysUser user1 = userMapper.selectById(1L);
			//现在还能查询出 user 对象
			Assert.assertNotNull(user1);
			//调用方法删除
			Assert.assertEquals(1, userMapper.deleteById(1L));
			//再次查询，这时应该没有值，为 null
			Assert.assertNull(userMapper.selectById(1L));

			//使用 SysUser 参数再做一遍测试，根据 id = 1001  查询
			SysUser user2 = userMapper.selectById(1001L);
			//现在还能查询出 user 对象
			Assert.assertNotNull(user2);
			//调用方法删除，注意这里使用参数为 user2
			Assert.assertEquals(1, userMapper.deleteById(user2));
			//再次查询，这时应该没有值，为 null
			Assert.assertNull(userMapper.selectById(1001L));
			//使用 SysUser 参数再做一遍测试
		} finally {
			//为了不影响数据库中的数据导致其他测试失败，这里选择回滚
			//由于默认的 sqlSessionFactory.openSession() 是不自动提交的，
			//因此不手动执行 commit 也不会提交到数据库
			sqlSession.rollback();
			//不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}


	@Test
	public void testSelectRolesByUserIdAndRoleEnabled(){
		SqlSession sqlSession = getSqlSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			//调用 selectRolesByUserIdAndRoleEnabled 方法查询用户的角色
			List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, null);
			//结果不为空
			Assert.assertNotNull(roleList);
			//角色数量大于 0 个
			Assert.assertTrue(roleList.size() > 0);
		} finally {
			//不要忘记关闭 sqlSession
			sqlSession.close();
		}
	}

	@Test
	public void testMyMapperProxy(){
		SqlSession sqlSession = getSqlSession();
		MyMapperProxy<UserMapper> userMapperMyMapperProxy = new MyMapperProxy<>(UserMapper.class,sqlSession);
		UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				new Class[]{UserMapper.class},userMapperMyMapperProxy);

		List<SysUser> list = userMapper.selectAll();
		System.out.println(list.size());

	}
	


}
