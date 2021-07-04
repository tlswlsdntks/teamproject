package kr.co.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.service.BoardService;
import kr.co.utils.Utils;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Inject
	private BoardService bService;

	@Inject
	private ServletContext sc;

	@Inject
	private String uploadPath;

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		
		String uploadPath = sc.getRealPath(this.uploadPath);
		Utils.makeDir(uploadPath);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardVO vo) {

		bService.insert(vo);
		return "redirect:/board/read/" + vo.getBno() + "?curPage=1";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		int curPage = 1;
		int amount = bService.getAmount();

		PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
		to.setAmount(amount);

		List<BoardVO> list = bService.list(to.getStartNum(), to.getPerPage());
		to.setList(list);

		model.addAttribute("to", to);
	}

	@RequestMapping(value = "/list/{curPage}", method = RequestMethod.GET)
	public String list(Model model, @PathVariable("curPage") Integer curPage) {
		int amount = bService.getAmount();

		PageTO<BoardVO> to = new PageTO<BoardVO>(curPage);
		to.setAmount(amount);

		List<BoardVO> list = bService.list(to.getStartNum(), to.getPerPage());
		to.setList(list);

		model.addAttribute("to", to);

		return "/board/list";
	}

	@RequestMapping(value = "/read/{bno}", method = RequestMethod.GET)
	public String read(@PathVariable("bno") Integer bno, Model model, @ModelAttribute("curPage") Integer curPage) {
		BoardVO vo = bService.read(bno);
		model.addAttribute("vo", vo);
		return "/board/read";
	}

	@RequestMapping(value = "/update/{bno}", method = RequestMethod.GET)
	public String update(@PathVariable("bno") Integer bno, Model model, @ModelAttribute("curPage") Integer curPage) {
		BoardVO vo = bService.update(bno);
		model.addAttribute("vo", vo);
		return "/board/update";
	}

	@RequestMapping(value = "/update/{bno}", method = RequestMethod.POST)
	public String update(@PathVariable("bno") Integer bno, BoardVO vo, int curPage) {
		vo.setBno(bno);
		bService.update(vo);
		return "redirect:/board/read/" + vo.getBno() + "?curPage=" + curPage;
	}

	@RequestMapping(value = "/delete/{bno}", method = RequestMethod.POST)
	public String delete(@PathVariable("bno") Integer bno, int curPage) {
		bService.delete(bno);
		return "redirect:/board/list/" + curPage;
	}

	@ResponseBody
	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public String uploadfile(MultipartHttpServletRequest request) throws Exception {
		MultipartFile file = request.getFile("file");
		String oriName = file.getOriginalFilename();
		String uploadPath = sc.getRealPath(this.uploadPath);

		return Utils.uploadFile(oriName, uploadPath, file);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public String deleteFile(String filename) {

		// DB 에서 파일 지우기
		bService.deleteFilename(filename);

		filename = filename.replace('/', File.separatorChar);
		String uploadPath = sc.getRealPath(this.uploadPath);
		String extendName = Utils.getExtendName(filename);
		MediaType mType = Utils.getMediaType(extendName);

		// 이미지 파일
		if (mType != null) {
			String systemFileName = Utils.getSystemFileName(filename);
			File f0 = new File(uploadPath, systemFileName);
			f0.delete();
		}

		File f1 = new File(uploadPath, filename);
		f1.delete();

		return filename;
	}

	@ResponseBody
	@RequestMapping(value = "/getAttach/{bno}", method = RequestMethod.GET)
	public List<String> getAttach(@PathVariable("bno") Integer bno) {
		System.out.println(bService.getAttach(bno));
		return bService.getAttach(bno);
	}

}
