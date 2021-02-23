package models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private int id;
	private int idUser;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String orderNote;
	private Timestamp dateCreate;
	private int status; // 1: đang xử lý - 2: đang giao hàng - 3: giao hàng thành công - 4: đã huỷ
	
	public Order(int idUser, String name, String phone, String email, String address, String orderNote) {
		super();
		this.idUser = idUser;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.orderNote = orderNote;
	}
	
}
