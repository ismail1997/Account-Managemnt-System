package sessionManagement;

import java.util.Date;

import entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import utils.Tools;
import utils.UserTools;

@Data @ToString @AllArgsConstructor @NoArgsConstructor
public class SessionManagement {
	private int sessionID;
	private User sessionUser;
	private String sessionDate;

	public static void main(String[] args) throws Exception {
		User user = UserTools.getOneUser(1);
		SessionManagement s  = new SessionManagement(1,user,new Date()+"");
		Tools.writeObjectAsStringToFileWithoutAppend(s, SessionManagementTools.filePath);
		System.out.println(s);
	}

}

