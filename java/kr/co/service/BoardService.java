package kr.co.service;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardService {

	void insert(BoardVO vo);

	List<BoardVO> list();

	BoardVO read(Integer bno);

	BoardVO update(Integer bno);

	void update(BoardVO vo);

	void delete(Integer bno);

	int getAmount();

	List<BoardVO> list(int startNum, int perPage);

	List<String> getAttach(Integer bno);

	void deleteFilename(String filename);

}
