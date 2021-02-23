package models;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	private int idUser;
	private ArrayList<CartDetail> list;
	private int number;
	
	public Cart(int idUser) {
		super();
		this.idUser = idUser;
	}
	
}
