package kr.co.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils {

	public static String makeDir(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);

		File f1 = new File(uploadPath, "" + year);
		File f2 = new File(f1, month < 10 ? "0" + month : "" + month);
		File f3 = new File(f2, date < 10 ? "0" + date : "" + date);

		if (!f1.exists())
			f1.mkdir();
		if (!f2.exists())
			f2.mkdir();
		if (!f3.exists())
			f3.mkdir();

		return f3.getAbsolutePath();
	}

	public static String reName(String oriName) {
		UUID uuid = UUID.randomUUID();
		String newName = uuid + "_" + oriName;
		return newName;
	}

	public static String getPathFileName(String path, String newName) {

		int idx = path.indexOf("uploads") + "uploads".length();
		String getPathFileName = path.substring(idx) + File.separator + newName;

		getPathFileName = getPathFileName.replace(File.separatorChar, '/');

		return getPathFileName;
	}

	public static String getExtendName(String oriName) {
		int idx = oriName.lastIndexOf(".");
		return oriName.substring(idx + 1);
	}

	public static MediaType getMediaType(String extendName) {
		Map<String, MediaType> map = new HashMap<String, MediaType>();
		map.put("JPG", MediaType.IMAGE_JPEG);
		map.put("JPEG", MediaType.IMAGE_JPEG);
		map.put("PNG", MediaType.IMAGE_PNG);
		map.put("GIF", MediaType.IMAGE_GIF);
		return map.get(extendName.toUpperCase());
	}

	public static String makeThumbnail(String path, String newName) throws Exception {
		int idx = newName.indexOf("_");
		String thumbnailName = newName.substring(0, idx) + "_s" + newName.substring(idx);

		// ??????????????? ??????
		File thumnailFile = new File(path, thumbnailName);
		// asdf_s_asdf.jpg

		// ?????????
		String extendName = Utils.getExtendName(newName).toUpperCase();

		// ?????? ?????????
		BufferedImage srcImg = ImageIO.read(new File(path, newName));
		BufferedImage desImg = Scalr.resize(srcImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, 100);

		ImageIO.write(desImg, extendName, thumnailFile);
		return getPathFileName(path, thumbnailName);
	}

	public static String uploadFile(String oriName, String uploadPath, MultipartFile file) throws Exception {

		// ?????? ??????
		String path = Utils.makeDir(uploadPath);
		String newName = Utils.reName(oriName);
		File target = new File(path, newName);
		FileCopyUtils.copy(file.getBytes(), target);

		// ????????? ?????? ????????????!
		String extendName = Utils.getExtendName(oriName);
		MediaType mediaType = Utils.getMediaType(extendName);

		if (mediaType != null) {
			System.out.println("????????? ???????????? ????????? ??????");
			return Utils.makeThumbnail(path, newName);
		} else {
			System.out.println("????????? ????????? ????????? ????????? ??????X");
			return Utils.getPathFileName(path, newName);
		}
	}

	public static String getSystemFileName(String filename) {
		String prefix = filename.substring(0, filename.indexOf("_s_"));
		String suffix = filename.substring(filename.indexOf("_s_") + 2);
		return prefix + suffix;
	}
	

}
