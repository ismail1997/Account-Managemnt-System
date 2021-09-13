package entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @ToString @NoArgsConstructor @AllArgsConstructor
public class Account {
	private int id;
	private String accountCode;
	private String dateOfCreation;
	private int userID;
	private double solde;
	private String typeOfAccount;
	private boolean active;
	
	
	
	
	
}
