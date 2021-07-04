package kr.co.persistence;

import java.util.List;

import kr.co.domain.BoardVO;

public interface BoardDAO {

	void insert(BoardVO vo);

	List<BoardVO> list();

	BoardVO read(Integer bno);

	void update(BoardVO vo);

	void delete(Integer bno);

	int getAmount();

	List<BoardVO> list(int startNum, int perPage);

	void increase(Integer bno);

	void addAttach(String filename, int bno);

	List<String> getAttach(Integer bno);

	void deleteFilename(String filename);

	void deleteAllFileByBno(int bno);

}
