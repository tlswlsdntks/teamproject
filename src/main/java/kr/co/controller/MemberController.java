package kr.co.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.service.MemberService;

@org.springframework.stereotype.Controller
@RequestMapping("/member")
public class MemberController {

	@Inject
	private MemberService mService;

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(MemberDTO dto) {
		mService.insert(dto);
		return "redirect:/member/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		List<MemberDTO> list = mService.list();
		model.addAttribute("list", list);
	}

	@RequestMapping(value = "/read/{userId}", method = RequestMethod.GET)
	public String read(@PathVariable("userId") String userId, Model model) {
		MemberDTO dto = mService.read(userId);
		model.addAttribute("dto", dto);
		return "/member/read";
	}

	@RequestMapping(value = "/update/{userId}", method = RequestMethod.GET)
	public String update(@PathVariable("userId") String userId, Model model) {
		MemberDTO dto = mService.read(userId);
		model.addAttribute("dto", dto);
		return "/member/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(MemberDTO dto, RedirectAttributes rttr) {
		mService.update(dto);
		// RedirectAttributes rttr
		// uri가 바뀌는 redirect임에도 불구하고, 데이터를 전송시킬 수 있다
		rttr.addFlashAttribute("test", "노출X");
		return "redirect:/member/read/" + dto.getUserId();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String userId) {
		mService.delete(userId);
		return "/member/list";
	}

	@RequestMapping(value = "/loginGet", method = RequestMethod.GET)
	public void loginGet() {

	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPost(LoginDTO login, Model model) {
		MemberDTO dto = mService.login(login);
		model.addAttribute("login", dto);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/board/list";
	}

}
