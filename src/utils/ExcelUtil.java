package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constants.GlobalConstant;
import daos.OrderDetailDao;
import models.Book;
import models.Order;
import models.OrderDetail;

public class ExcelUtil {
	// Xuất đơn hàng ra file excel
	public static void exportOrder(ArrayList<Order> list) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		// sheet 1: Order
		XSSFSheet sheet = workbook.createSheet("Order");
		int rownum = 0;
		Cell cell;
		Row row;
		XSSFCellStyle style = createStyleForTitle(workbook);
		row = sheet.createRow(rownum);
		// STT
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("STT");
		cell.setCellStyle(style);
		// ID
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Mã đơn hàng");
		cell.setCellStyle(style);
		// Name
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Tên");
		cell.setCellStyle(style);
		// SĐT
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("SĐT");
		cell.setCellStyle(style);
		// Address
		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Địa chỉ");
		cell.setCellStyle(style);
		// Ngày mua
		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("Ngày mua");
		cell.setCellStyle(style);
		// Trạng thái
		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("Trạng thái");
		cell.setCellStyle(style);
		for (int k = 0; k <= 6; k++) {
			sheet.autoSizeColumn(k);
		}
		// Data
		if (list.size() > 0) {
			int stt = 1;
			for (Order order : list) {
				rownum++;
				row = sheet.createRow(rownum);
				// STT
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue(stt++);
				// ID
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue(order.getId());
				// Name
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue(order.getName());
				// SĐT
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue(order.getPhone());
				// Address
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(order.getAddress());
				// Ngày mua
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue(StringUtil.tachNgay(order.getDateCreate()));
				// Trạng thái
				cell = row.createCell(6, CellType.STRING);
				if (order.getStatus() == 0) {
					cell.setCellValue("Đang/Chưa giao hàng");
				} else {
					cell.setCellValue("Giao hàng thành công");
				}
				for (int i = 2; i <= 6; i++) {
					sheet.autoSizeColumn(i);
				}
			}
		}
		// sheet 2: Order Detail
		XSSFSheet sheet2 = workbook.createSheet("Order Detail");
		XSSFCellStyle style2 = createStyleForThanhTien(workbook);
		rownum = 0;
		row = sheet2.createRow(rownum);
		// Mã đơn hàng
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Mã đơn hàng");
		cell.setCellStyle(style);
		// STT
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("STT");
		cell.setCellStyle(style);
		// Sản phẩm
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Sách");
		cell.setCellStyle(style);
		// Số lượng
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("Số lượng");
		cell.setCellStyle(style);
		// Giá gốc
		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Giá gốc");
		cell.setCellStyle(style);
		// Giảm giá
		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("Giảm giá");
		cell.setCellStyle(style);
		// Giá bán
		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("Giá bán");
		cell.setCellStyle(style);
		// Tổng tiền
		cell = row.createCell(7, CellType.STRING);
		cell.setCellValue("Tổng tiền");
		cell.setCellStyle(style);
		for (int k = 0; k <= 7; k++) {
			sheet2.autoSizeColumn(k);
		}
		// Data
		if (list.size() > 0) {
			OrderDetailDao objDetailDao = new OrderDetailDao();
			for (Order order : list) {
				ArrayList<OrderDetail> listDetail = objDetailDao.getOrderDetail(order.getId());
				if (listDetail.size() > 0) {
					int stt = 1, thanhTien = 0;
					for (int i = 0; i <= listDetail.size() + 1; i++) {
						rownum++;
						row = sheet2.createRow(rownum);
						if (i == 0) {
							// Mã đơn hàng
							cell = row.createCell(0, CellType.STRING);
							cell.setCellValue(order.getId());
						}
						if (i < listDetail.size()) {
							OrderDetail detail = listDetail.get(i);
							Book book = GetObjUtil.getBook(detail.getIdBook());
							// STT
							cell = row.createCell(1, CellType.STRING);
							cell.setCellValue(stt++);
							// Sản phẩm
							cell = row.createCell(2, CellType.STRING);
							cell.setCellValue(book.getName());
							// Số lượng
							cell = row.createCell(3, CellType.STRING);
							cell.setCellValue(detail.getNumber());
							// Giá gốc
							cell = row.createCell(4, CellType.STRING);
							cell.setCellValue(detail.getPrice() + " VNĐ");
							// Giảm giá
							cell = row.createCell(5, CellType.STRING);
							cell.setCellValue(detail.getSale() + " %");
							// Giá bán
							int giaBan = (detail.getPrice() * (100 - detail.getSale())) / 100;
							cell = row.createCell(6, CellType.STRING);
							cell.setCellValue(giaBan + " VNĐ");
							// Tổng tiền
							int tongTien = giaBan * detail.getNumber();
							cell = row.createCell(7, CellType.STRING);
							cell.setCellValue(tongTien + " VNĐ");
							thanhTien += tongTien;
						}

						if (i == listDetail.size()) {
							// Ship
							int ship = 0;
							if (thanhTien < 300000) {
								ship = GlobalConstant.SHIP;
								thanhTien += ship;
							}
							cell = row.createCell(6, CellType.STRING);
							cell.setCellValue("Phí ship");
							cell = row.createCell(7, CellType.STRING);
							cell.setCellValue(ship + " VNĐ");
						}

						if (i > listDetail.size()) {
							// Thành tiền
							cell = row.createCell(6, CellType.STRING);
							cell.setCellValue("Thành tiền");
							cell.setCellStyle(style2);
							cell = row.createCell(7, CellType.STRING);
							cell.setCellValue(thanhTien + " VNĐ");
							cell.setCellStyle(style2);
						}
						for (int j = 2; j <= 7; j++) {
							if (j != 3) {
								sheet2.autoSizeColumn(j);
							}
						}
					}
				}
			}
		}

		String day = StringUtil.getDay();
		String fileName = "order" + day + ".xlsx";
		String path = "F:\\ShopBook";
		File saveDir = new File(path);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		String filePath = path + File.separator + fileName;
		File file = new File(filePath);
		FileOutputStream outFile = new FileOutputStream(file);
		workbook.write(outFile);
	}

	public static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 14);
		font.setColor(IndexedColors.RED.getIndex());
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}
	
	public static XSSFCellStyle createStyleForThanhTien(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

}
