package utils;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import constants.GlobalConstant;
import models.Book;
import models.OrderDetail;

public class EmailUtil {
	public static String emailContentOrder(ArrayList<OrderDetail> list, String name) {
		int tongGia = 0;
		String content = "";
		content += "<p>Xin chào " + name + ",</p>";
		content += "<p>Cảm ơn quý khách đã tin tưởng và lựa chọn dịch vụ sản phẩm tại cửa hàng sách <span style='color: #BB0000; font-weight: bold'>SHOPBOOK</span> của chúng tôi.</p>";
		content += "<p>Chi tiết đơn hàng:</p>";
		content += "<table width='800px'>";
		content += "<tr><th width='5%'>#</th><th>TÊN SẢN PHẨM</th><th>ĐƠN GIÁ</th><th>SỐ LƯỢNG</th><th>THÀNH TIỀN</th></tr>";
		if (list.size() > 0) {
			int stt = 1;
			for (OrderDetail orderDetail : list) {
				Book book = GetObjUtil.getBook(orderDetail.getIdBook());
				int giaBan = (orderDetail.getPrice() * (100 - orderDetail.getSale())) / 100;
				int thanhTien = giaBan * orderDetail.getNumber();
				tongGia += thanhTien;
				content += "<tr><td align='center'>" + stt++ + "</td><td align='center'>" + book.getName()
						+ "</td><td align='center'>" + giaBan + " VNĐ</td><td align='center'>" + orderDetail.getNumber()
						+ "</td><td align='center'>" + thanhTien + " VNĐ</td></tr>";
			}
		}
		content += "</table>";
		content += "<p><span style='font-weight: bold'>TỔNG GIÁ: </span><span style='font-weight: bold; color: #BB0000'>"
				+ tongGia + " VNĐ</span></p>";
		content += "<p>Chúc mừng bạn đã đặt hàng thành công, đơn hàng sẽ được giao trong vài ngày tới.</p>";
		content += "<p>Nếu có bất kỳ vấn đề gì, hãy gọi ngay đến tổng đài trợ giúp của chúng tôi tại đây!</p>";
		content += "<p>Hotline: " + GlobalConstant.HOTLINE + "</p>";
		return content;
	}

	public static String emailContentDeliverySuccess(ArrayList<OrderDetail> list, String name) {
		int tongSoLuong = 0;
		int tongGia = 0;
		if (list.size() > 0) {
			for (OrderDetail orderDetail : list) {
				int giaBan = (orderDetail.getPrice() * (100 - orderDetail.getSale())) / 100;
				int thanhTien = giaBan * orderDetail.getNumber();
				tongGia += thanhTien;
				tongSoLuong += orderDetail.getNumber();
			}
		}
		String content = "";
		content += "<p>Xin chào " + name + ",</p>";
		content += "<p>Cảm ơn quý khách đã tin tưởng và lựa chọn dịch vụ sản phẩm tại cửa hàng sách <span style='color: #BB0000; font-weight: bold'>SHOPBOOK</span> của chúng tôi.</p>";
		content += "<p>Thông tin đơn hàng: Số lượng sản phẩm [" + tongSoLuong + "]</p>";
		content += "<p>Chi tiết:</p><p>";
		if (list.size() > 0) {
			for (OrderDetail orderDetail : list) {
				Book book = GetObjUtil.getBook(orderDetail.getIdBook());
				content += "	" + book.getName() + " [" + orderDetail.getNumber() + "]<br />";
			}
		}
		content += "</p><p><span style='font-weight: bold'>TỔNG GIÁ: </span><span style='font-weight: bold; color: #BB0000'>"
				+ tongGia + " VNĐ</span></p>";
		content += "<p>Đơn hàng đã được giao thành công.</p>";
		content += "<p>Nếu có bất kỳ vấn đề gì, hãy gọi ngay đến tổng đài trợ giúp của chúng tôi tại đây!</p>";
		content += "<p>Hotline: " + GlobalConstant.HOTLINE + "</p>";
		return content;
	}
	
	public static String emailContentCancelOrder(ArrayList<OrderDetail> list, String name) {
		int tongSoLuong = 0;
		int tongGia = 0;
		if (list.size() > 0) {
			for (OrderDetail orderDetail : list) {
				int giaBan = (orderDetail.getPrice() * (100 - orderDetail.getSale())) / 100;
				int thanhTien = giaBan * orderDetail.getNumber();
				tongGia += thanhTien;
				tongSoLuong += orderDetail.getNumber();
			}
		}
		String content = "";
		content += "<p>Xin chào " + name + ",</p>";
		content += "<p>Cảm ơn quý khách đã tin tưởng và lựa chọn dịch vụ sản phẩm tại cửa hàng sách <span style='color: #BB0000; font-weight: bold'>SHOPBOOK</span> của chúng tôi.</p>";
		content += "<p>Thông tin đơn hàng: Số lượng sản phẩm [" + tongSoLuong + "]</p>";
		content += "<p>Chi tiết:</p><p>";
		if (list.size() > 0) {
			for (OrderDetail orderDetail : list) {
				Book book = GetObjUtil.getBook(orderDetail.getIdBook());
				content += "	" + book.getName() + " [" + orderDetail.getNumber() + "]<br />";
			}
		}
		content += "</p><p><span style='font-weight: bold'>TỔNG GIÁ: </span><span style='font-weight: bold; color: #BB0000'>"
				+ tongGia + " VNĐ</span></p>";
		content += "<p>Đã huỷ đơn hàng thành công.</p>";
		content += "<p>Nếu có bất kỳ vấn đề gì, hãy gọi ngay đến tổng đài trợ giúp của chúng tôi tại đây!</p>";
		content += "<p>Hotline: " + GlobalConstant.HOTLINE + "</p>";
		return content;
	}

	public static void sendMail(String to, String subject, String content, String user, String pass) {
		Properties pro = System.getProperties();
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.port", "587");
		pro.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(pro, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject, "UTF-8");
			message.setContent(content, "text/html; charset=UTF-8");
			Transport.send(message);
			System.out.println("Send Email Success");
		} catch (AddressException e) {
			System.err.println("Send Email Failed");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.err.println("Send Email Failed");
			e.printStackTrace();
		}
	}

}