package kr.co.service;

import java.util.List;

import kr.co.domain.ReplyVO;

public interface ReplyService {

	void insert(ReplyVO vo);

	List<ReplyVO> list(Integer bno);

	int delete(int rno);

	int update(ReplyVO vo);

}
