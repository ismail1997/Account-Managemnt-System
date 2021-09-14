package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @ToString @AllArgsConstructor @NoArgsConstructor
public class Reclamation {
	private int id;
	private String message;
	private String dateOfReclamation;
	private boolean answered;
	private int userID;
	private String answer;
}
