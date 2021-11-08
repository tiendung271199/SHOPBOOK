package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import constants.GlobalConstant;

public class FileUtil {

	public static String rename(String fileName) {
		String nameFile = "";
		if (!fileName.isEmpty()) {
			String[] arrImg = fileName.split("\\.");
			String duoiFileImg = arrImg[arrImg.length - 1];

			for (int i = 0; i < (arrImg.length - 1); i++) {
				if (i == 0) {
					nameFile = arrImg[i];
				} else {
					nameFile += "-" + arrImg[i];
				}
			}
			nameFile = nameFile + "-" + System.nanoTime() + "." + duoiFileImg;
		}
		return nameFile;
	}

	public static String getName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
	
	public static boolean checkImage(String fileName) {
		if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png") && !fileName.endsWith(".jpeg")
				&& !fileName.endsWith(".gif")) {
			return false;
		}
		return true;
	}
	
	// Method upload multiple file
	public static ArrayList<String> uploadMultipleFile(HttpServletRequest request) {
		ArrayList<String> list = new ArrayList<String>();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String dirPath = request.getServletContext().getRealPath("") + GlobalConstant.DIR_UPLOAD;
				File saveDir = new File(dirPath);
				if (!saveDir.exists()) {
					saveDir.mkdirs();
				}
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						name = rename(name);
						String filePath = dirPath + File.separator + name;
						item.write(new File(filePath));
						list.add(name);
					}
				}
				System.out.println("File uploaded successfully");
			} catch (Exception ex) {
				System.out.println("File upload failed due to : " + ex);
			}
		} else {
			System.out.println("Sorry this servlet only handles file upload request");
		}
		return list;
	}

	// Method upload file
	public static String upload(String nameInput, HttpServletRequest request) throws IOException, ServletException {
		Part filePart = request.getPart(nameInput);
		String fileName = rename(filePart.getSubmittedFileName());
		if (fileName.equals("")) {
			return "";
		}
		if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png") && !fileName.endsWith(".jpeg")
				&& !fileName.endsWith(".gif")) {
			return "";
		}
		String dirPath = request.getServletContext().getRealPath("") + GlobalConstant.DIR_UPLOAD;
		File saveDir = new File(dirPath);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		String filePath = dirPath + File.separator + fileName;
		filePart.write(filePath);
		return fileName;
	}

	// Method delete file
	public static boolean delFile(String fileName, HttpServletRequest request) {
		if (!fileName.equals("")) {
			String filePath = request.getServletContext().getRealPath("") + GlobalConstant.DIR_UPLOAD + File.separator
					+ fileName;
			File file = new File(filePath);
			return file.delete();
		}
		return false;
	}

}
