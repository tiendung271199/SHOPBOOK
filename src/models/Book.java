package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private int id;
	private String name;
	private String author;
	private String description;
	private String detail;
	private int number;
	private int price;
	private int purchases;
	private String picture;
	private Category cat;
}
