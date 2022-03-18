package sessionManagement;



import entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @ToString @AllArgsConstructor @NoArgsConstructor
public class SessionManagement {
	private int sessionID;
	private User sessionUser;
	private String sessionDate;



}

