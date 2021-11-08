package models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reviews {
	private int id;
	private int idUser;
	private int idBook;
	private double star;
	private String comment;
	private Timestamp dateCreate;
	private int status;
	
	public Reviews(int idUser, int idBook, double star, String comment) {
		super();
		this.idUser = idUser;
		this.idBook = idBook;
		this.star = star;
		this.comment = comment;
	}
	
}
