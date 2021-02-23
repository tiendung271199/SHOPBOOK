package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
	private int id;
	private int idOrder;
	private int number;
	private int idBook;
	private int sale;
	private int price;
}
