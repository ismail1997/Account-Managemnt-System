package sessionManagement;

import java.util.ArrayList;
import java.util.Date;

import entities.User;
import utils.Tools;
import utils.UserTools;

public class SessionManagementTools {

	public static final String filePath ="C:\\Users\\ismail\\Documents\\Applications\\applicationsData\\javaAppsData\\AccountManagementSystem\\settings\\sharedPreferences\\session.txt";
	private static void writeSessionToFile(SessionManagement session) throws Exception {
		Tools.writeObjectAsStringToFileWithoutAppend(session, filePath);
	}
	
	
	public  static void startSession(SessionManagement session) throws Exception {
		writeSessionToFile(session);
	}
	
	public static ArrayList<String> getSessionFromFile() {
		return Tools.getDataFromFile(filePath);
	}
	
	public static SessionManagement getSession() {
		ArrayList<String> l = getSessionFromFile();
		if(l.isEmpty() || l==null) return null;
		String sm =l.get(0);
		
		String sessionID=sm.substring(sm.indexOf("sessionID=")+"sessionID=".length(), sm.indexOf("sessionUser")-2);
		String sessionUser = sm.substring(sm.indexOf("sessionUser=")+"sessionUser=".length(),sm.indexOf("sessionDate=")-2);
		String sessionDate = sm.substring(sm.indexOf("sessionDate=")+"sessionDate=".length(),sm.length()-1);
		
		String user[] = sessionUser.split(",");
		
		User u = new User();
		
		String id = user[0].replaceAll("User\\(id=", "");
		u.setId(Integer.parseInt(id));
		
		String codeUser=user[1].replaceAll(" codeUser=", "");
		u.setCodeUser(codeUser);
		
		String firstName= user[2].replaceAll(" firstName=","");
		u.setFirstName(firstName);
		
		String lastName= user[3].replaceAll(" lastName=","");
		u.setLastName(lastName);
		
		String phoneNumber= user[4].replaceAll(" phoneNumber=","");
		u.setPhoneNumber(phoneNumber);
		
		String email= user[5].replaceAll(" email=","");
		u.setEmail(email);
		
		String password= user[6].replaceAll(" password=","");
		u.setPassword(password);
		
		String dateOfBirth= user[7].replaceAll(" dateOfBirth=","");
		u.setDateOfBirth(dateOfBirth);
		
		String address = user[8].replaceAll(" address=", "");
		u.setAddress(address);
		
		String userType = user[9].replaceAll(" userType=", "").replaceAll("\\)","");
		u.setUserType(userType);
		
		
		SessionManagement s = new SessionManagement();
		s.setSessionID(Integer.parseInt(sessionID));
		s.setSessionUser(u);
		s.setSessionDate(sessionDate);
		
		return s;
	}
	
	public static void closeSession() {
		
	}
	
	public static void main(String[] args) throws Exception {
//		User user = UserTools.getOneUser(1);
//		SessionManagement sm = new SessionManagement(1, user, new Date()+"");
//		startSession(sm);
		System.out.println(getSession());
		
//		
		//getSession().forEach(System.out::println);
	}
}
