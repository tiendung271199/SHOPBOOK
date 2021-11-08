package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import daos.ForbiddenWordDao;

public class StringUtil {
	public static String getThu(int day, int month, int year) {
		String thu = "";
		int jmd = (day + ((153 * (month + 12 * ((14 - month) / 12) - 3) + 2) / 5)
				+ (365 * (year + 4800 - ((14 - month) / 12))) + ((year + 4800 - ((14 - month) / 12)) / 4)
				- ((year + 4800 - ((14 - month) / 12)) / 100) + ((year + 4800 - ((14 - month) / 12)) / 400) - 32045)
				% 7;
		switch (jmd) {
		case 0:
			thu = "Thứ 2";
			break;
		case 1:
			thu = "Thứ 3";
			break;
		case 2:
			thu = "Thứ 4";
			break;
		case 3:
			thu = "Thứ 5";
			break;
		case 4:
			thu = "Thứ 6";
			break;
		case 5:
			thu = "Thứ 7";
			break;
		case 6:
			thu = "Chủ nhật";
			break;
		default:
			break;
		}
		return thu;
	}

	public static boolean checkWord(String str) {
		ForbiddenWordDao objWordDao = new ForbiddenWordDao();
		String[] arr = str.split("\\s");
		for (int i = 0; i <= arr.length - 1; i++) {
			if (objWordDao.checkWord(arr[i])) {
				return true;
			}
		}
		return false;
	}

	public static String tachNgay(Timestamp time) {
		String kq = "";
		String str = time.toString();
		String[] arr = str.split("\\s");
		String s = arr[0];
		String[] arr2 = s.split("\\-");
		for (int i = arr2.length - 1; i >= 0; i--) {
			if (i == 0) {
				kq += arr2[i];
			} else {
				kq += arr2[i] + "/";
			}
		}
		return kq;
	}

	public static String tachChuoi(Timestamp time) {
		String kq = "";
		String str = time.toString();
		String[] arr = str.split("\\s");
		String s2 = arr[1];
		String[] arr3 = s2.split("\\:");
		kq += arr3[0] + ":" + arr3[1] + " ";
		String s = arr[0];
		String[] arr2 = s.split("\\-");
		kq += getThu(Integer.parseInt(arr2[2]), Integer.parseInt(arr2[1]), Integer.parseInt(arr2[0])) + " Ngày ";
		for (int i = arr2.length - 1; i >= 0; i--) {
			if (i == 0) {
				kq += arr2[i];
			} else {
				kq += arr2[i] + "/";
			}
		}
		return kq;
	}

	// tách chuỗi picture
	public static ArrayList<String> getFileName(String picture) {
		ArrayList<String> list = new ArrayList<String>();
		String[] arr = picture.split("\\/");
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return list;
	}

	// Lấy filename đầu tiên
	public static String fileName(String picture) {
		String[] arr = picture.split("\\/");
		return arr[0];
	}
	
	// Lấy ngày giờ hiện tại
	public static String getDay() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		return sdf.format(date);
	}

	// Lấy năm hiện tại
	public static int getYearNow() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return Integer.parseInt(sdf.format(date));
	}

	// Lấy tháng
	public static int getMonth(Timestamp time) {
		String str = time.toString();
		String[] arr = str.split("\\s");
		String[] arr2 = arr[0].split("\\-");
		return Integer.parseInt(arr2[1]);
	}

	// Lấy năm
	public static int getYear(Timestamp time) {
		String str = time.toString();
		String[] arr = str.split("\\s");
		String[] arr2 = arr[0].split("\\-");
		return Integer.parseInt(arr2[0]);
	}

	// Dùng để mã hoá password
	public static String md5(String str) {
		MessageDigest md;
		String result = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			BigInteger bi = new BigInteger(1, md.digest());
			result = bi.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String makeSlug(String title) {
		String slug = Normalizer.normalize(title, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		slug = pattern.matcher(slug).replaceAll("");
		slug = slug.toLowerCase();
		slug = slug.replaceAll("đ", "d");
		slug = slug.replaceAll("([^0-9a-z-\\s])", "");
		slug = slug.replaceAll("[\\s]", "-");
		slug = slug.replaceAll("(-+)", "-");
		slug = slug.replaceAll("^-+", "");
		slug = slug.replaceAll("-+$", "");
		return slug;
	}

}
