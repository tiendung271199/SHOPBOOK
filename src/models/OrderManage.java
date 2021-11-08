package models;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderManage {
	private int id;
	private int idUser;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String orderNote;
	private String dateCreate;
	private int status;
	ArrayList<OrderManageDetail> list;
	
}
