package kr.co.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession bSession;

	private final String NAMESPACE = "kr.co.board";

	@Override
	public void insert(BoardVO vo) {
		bSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<BoardVO> list() {
		return bSession.selectList(NAMESPACE + ".list");
	}

	@Override
	public BoardVO read(Integer bno) {
		return bSession.selectOne(NAMESPACE + ".read", bno);
	}

	@Override
	public void update(BoardVO vo) {
		bSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public void delete(Integer bno) {
		bSession.delete(NAMESPACE + ".delete", bno);
	}

	@Override
	public int getAmount() {
		return bSession.selectOne(NAMESPACE + ".getAmount");
	}

	@Override
	public List<BoardVO> list(int startNum, int perPage) {
		RowBounds rb = new RowBounds(startNum - 1, perPage);
		return bSession.selectList(NAMESPACE + ".list", null, rb);
	}

	@Override
	public void increase(Integer bno) {
		bSession.update(NAMESPACE + ".increase", bno);
	}

	@Override
	public void addAttach(String file, int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("filename", file);
		map.put("bno", bno);
		bSession.insert(NAMESPACE + ".addAttach", map);
	}

	@Override
	public List<String> getAttach(Integer bno) {
		return bSession.selectList(NAMESPACE + ".getAttach", bno);
	}

	@Override
	public void deleteFilename(String filename) {
		bSession.delete(NAMESPACE + ".deleteFilename", filename);
	}

	@Override
	public void deleteAllFileByBno(int bno) {
		bSession.delete(NAMESPACE + ".deleteAllFileByBno", bno);
	}

}
