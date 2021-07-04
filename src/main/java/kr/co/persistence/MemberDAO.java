package kr.co.persistence;

import java.util.List;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;

public interface MemberDAO {

	void insert(MemberDTO dto);

	List<MemberDTO> list();

	MemberDTO read(String userId);

	void update(MemberDTO dto);

	void delete(String userId);

	MemberDTO login(LoginDTO login);

}
