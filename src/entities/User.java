package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @ToString @NoArgsConstructor
public class User {
	
	private int id;
	private String codeUser;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email ;
	private String password;
	private String dateOfBirth;
	private String address;
	private String userType;
	
	


}
