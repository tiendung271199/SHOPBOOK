package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favourite {
	private int id;
	private int idUser;
	private int idBook;
	private int status;
	
	public Favourite(int idUser, int idBook) {
		super();
		this.idUser = idUser;
		this.idBook = idBook;
	}
	
}
