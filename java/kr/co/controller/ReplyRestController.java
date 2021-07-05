package kr.co.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import kr.co.domain.ReplyVO;
import kr.co.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyRestController {

	@Inject
	private ReplyService rService;

	@RequestMapping(value = "/reply", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public String insert(@RequestBody Map<String, Object> map) {
		int bno = Integer.parseInt(map.get("bno").toString());
		String replyer = map.get("replyer").toString();
		String replyText = map.get("replyText").toString();
		ReplyVO vo = new ReplyVO(-1, bno, replyer, replyText, null, null);
		rService.insert(vo);
		return "입력이 완료됐습니다!";
	}
	
	@RequestMapping(value = "/reply/{bno}", method = RequestMethod.GET)
	public List<ReplyVO> list(@PathVariable("bno") Integer bno) {
		return rService.list(bno);
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.DELETE)
	public int delete(@RequestBody Map<String, Object> map) {
		int rno = Integer.parseInt(map.get("rno").toString());
		return rService.delete(rno);
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.PUT)
	public int update(@RequestBody Map<String, Object> map) {
		int rno = Integer.parseInt(map.get("rno").toString());
		String replyer = map.get("replyer").toString();
		String replyText = map.get("replyText").toString();
		ReplyVO vo = new ReplyVO(rno, -1, replyer, replyText, null, null);
		
		return rService.update(vo);
	}

}
