package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {

	@Inject
	private MemberDAO mDao;

	@Override
	public void insert(MemberDTO dto) {
		mDao.insert(dto);
	}

	@Override
	public List<MemberDTO> list() {
		return mDao.list();
	}

	@Override
	public MemberDTO read(String userId) {
		return mDao.read(userId);
	}

	@Override
	public void update(MemberDTO dto) {
		mDao.update(dto);
	}

	@Override
	public void delete(String userId) {
		mDao.delete(userId);
	}

	@Override
	public MemberDTO login(LoginDTO login) {
		return mDao.login(login);
	}
}
