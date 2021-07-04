package kr.co.ezen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.utils.Utils;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private ServletContext sc;
	
	@Inject
	private String uploadPath;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}

	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadForm() {

	}

	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public String uploadForm(MultipartHttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		MultipartFile file = request.getFile("file");
		String oriName = file.getOriginalFilename();
		String newName = Utils.reName(oriName);

		String uploadPath = sc.getRealPath(this.uploadPath);

		String path = Utils.makeDir(uploadPath);
		File target = new File(path, newName);
		FileCopyUtils.copy(file.getBytes(), target);

		return "/noajax";
	}

	@RequestMapping(value = "/noajaxForm", method = RequestMethod.GET)
	public String noajaxForm() {

		return "/noajax";
	}

	@RequestMapping(value = "/noajaxForm", method = RequestMethod.POST)
	public String noajaxForm(MultipartHttpServletRequest request, Model model) throws Exception {

		MultipartFile file = request.getFile("file");
		String oriName = file.getOriginalFilename();
		String newName = Utils.reName(oriName);

		String uploadPath = sc.getRealPath(this.uploadPath);

		String path = Utils.makeDir(uploadPath);
		File target = new File(path, newName);
		FileCopyUtils.copy(file.getBytes(), target);

		String pathFileName = Utils.getPathFileName(path, newName);
		model.addAttribute("pathFileName", pathFileName);

		return "/uploadResult";
	}

	@RequestMapping(value = "/noajax", method = RequestMethod.POST)
	public String noajax(MultipartHttpServletRequest request, Model model) throws Exception {

		MultipartFile file = request.getFile("file");
		String oriName = file.getOriginalFilename();

		String uploadPath = sc.getRealPath(this.uploadPath);

		String pathFileName = Utils.uploadFile(oriName, uploadPath, file);
		model.addAttribute("pathFileName", pathFileName);
		return "uploadResult";
	}

	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {

	}

	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public String uploadAjax(MultipartHttpServletRequest request) throws Exception {
		MultipartFile file = request.getFile("file");
		String oriName = file.getOriginalFilename();
		String uploadPath = sc.getRealPath(this.uploadPath);
		return Utils.uploadFile(oriName, uploadPath, file);
	}

	@ResponseBody
	// 파일은 byte 와 headers 로 구현되어있다
	// ResponseEntity<byte[]> : byte + headers
	@RequestMapping(value = "/displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String filename) {
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;

		try {

			String uploadPath = sc.getRealPath(this.uploadPath);
			in = new FileInputStream(uploadPath + filename);

			String extendName = Utils.getExtendName(filename);
			MediaType mType = Utils.getMediaType(extendName);

			HttpHeaders headers = new HttpHeaders();
			if (mType != null)
				headers.setContentType(mType);
			else {
				filename = filename.substring(filename.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition",
						"attachment:filename=\"" + new String(filename.getBytes("UTF-8"), "ISO-8859-1"));
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return entity;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public String deleteFile(String filename) {

		String uploadPath = sc.getRealPath(this.uploadPath);

		// uri -> 디렉토리 변경
		filename = filename.replace('/', File.separatorChar);

		String extendName = Utils.getExtendName(filename);
		MediaType mType = Utils.getMediaType(extendName);

		// 이미지 파일이 아닐 때
		if (mType != null) {
			String systemFileName = Utils.getSystemFileName(filename);
			File f0 = new File(uploadPath, systemFileName);
			f0.delete();
		}
		
		// 이미지 파일일 때
		File f1 = new File(uploadPath, filename);
		System.out.println(filename);
		f1.delete();
		
		return "삭제 완료";
	}
	
	
	@RequestMapping(value = "/testInterceptor", method = RequestMethod.GET)
	public String testInterceptor(Model model) {
		
		System.out.println("Controller:::::::::::::::::::");
		model.addAttribute("hello", "헬로우");
		
		return "/home";
	}

}
