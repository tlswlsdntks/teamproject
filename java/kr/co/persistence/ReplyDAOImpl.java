package kr.co.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession rSession;

	private final String NAMESPACE = "kr.co.reply";

	@Override
	public void insert(ReplyVO vo) {
		rSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ReplyVO> list(Integer bno) {
		return rSession.selectList(NAMESPACE + ".list", bno);
	}

	@Override
	public int delete(int rno) {
		return rSession.delete(NAMESPACE + ".delete", rno);
	}

	@Override
	public int update(ReplyVO vo) {
		return rSession.update(NAMESPACE+".update", vo);
	}

	@Override
	public void deleteByBno(Integer bno) {
		rSession.delete(NAMESPACE+".deleteByBno", bno);
	}
}
