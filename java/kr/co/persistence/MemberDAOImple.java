package kr.co.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;

@Repository
public class MemberDAOImple implements MemberDAO {

	@Inject
	private SqlSession session;
	private final String NAMESPACE = "kr.co.member";

	@Override
	public void insert(MemberDTO dto) {
		session.insert(NAMESPACE + ".insert", dto);
	}

	@Override
	public List<MemberDTO> list() {
		return session.selectList(NAMESPACE + ".list");
	}

	@Override
	public MemberDTO read(String userId) {
		return session.selectOne(NAMESPACE + ".read", userId);
	}

	@Override
	public void update(MemberDTO dto) {
		session.update(NAMESPACE + ".update", dto);
	}

	@Override
	public void delete(String userId) {
		session.delete(NAMESPACE + ".delete", userId);
	}

	@Override
	public MemberDTO login(LoginDTO login) {
		return session.selectOne(NAMESPACE+".login", login);
	}

}
