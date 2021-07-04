package kr.co.ezen;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

public class SqlSessionFacotyTest {

	@Inject
	private SqlSessionFactory sqlFactory;

	@Test
	public void testFactory() {
		System.out.println(sqlFactory);
	}

	@Test
	public void testSession() {
		SqlSession sqlSession = null;

		try {
			sqlSession = sqlFactory.openSession();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			if(sqlSession!=null)
				try {
					sqlSession.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
}
