package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderManageDetail {
	private int number;
	private int idBook;
	private int sale;
	private int price;
}
