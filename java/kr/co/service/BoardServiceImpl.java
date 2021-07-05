package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.domain.BoardVO;
import kr.co.persistence.BoardDAO;
import kr.co.persistence.ReplyDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO bDao;

	@Inject
	private ReplyDAO rDao;

	@Override
	public void insert(BoardVO vo) {
		// bno 먼저 생성
		bDao.insert(vo);

		String[] files = vo.getFiles();

		if (files == null)
			return;

		for (String filename : files)
			bDao.addAttach(filename, vo.getBno());
	}

	@Override
	public List<BoardVO> list() {
		return bDao.list();
	}

	@Override
	@Transactional
	public BoardVO read(Integer bno) {
		bDao.increase(bno);
		return bDao.read(bno);
	}

	@Override
	public BoardVO update(Integer bno) {
		return bDao.read(bno);
	}

	@Override
	public void update(BoardVO vo) {
		bDao.update(vo);

		// 기존에 있던 파일을 다시 지운다!
		bDao.deleteAllFileByBno(vo.getBno());

		// 기존에 있는 파일과 수정 시에 올린 파일을 다시 한번에 등록한다!
		if (vo.getFiles() != null) {
			for (String filename : vo.getFiles())
				bDao.addAttach(filename, vo.getBno());
		}
	}

	@Override
	@Transactional
	public void delete(Integer bno) {
		bDao.deleteAllFileByBno(bno);
		rDao.deleteByBno(bno);
		bDao.delete(bno);
	}

	@Override
	public int getAmount() {
		return bDao.getAmount();
	}

	@Override
	public List<BoardVO> list(int startNum, int perPage) {
		return bDao.list(startNum, perPage);
	}

	@Override
	public List<String> getAttach(Integer bno) {
		return bDao.getAttach(bno);
	}

	@Override
	public void deleteFilename(String filename) {
		bDao.deleteFilename(filename);
	}

}
