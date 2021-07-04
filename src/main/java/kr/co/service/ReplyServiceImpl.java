package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.domain.ReplyVO;
import kr.co.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO rDao;

	@Override
	public void insert(ReplyVO vo) {
		rDao.insert(vo);
	}

	@Override
	public List<ReplyVO> list(Integer bno) {
		return rDao.list(bno);
	}

	@Override
	public int delete(int rno) {
		return rDao.delete(rno);
	}

	@Override
	public int update(ReplyVO vo) {
		return rDao.update(vo);
	}
	
	
}
