package utils;

 
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import entities.Account;
import entities.User;
import sessionManagement.SessionManagementTools;

public class UserTools {
	
	public static final String filePath =Tools.DirecotryOfDocuments+"\\Applications\\applicationsData\\javaAppsData\\AccountManagementSystem\\persons\\persons.txt";
	public static final String userHistoryFilePath=Tools.DirecotryOfDocuments+"\\Applications\\applicationsData\\javaAppsData\\AccountManagementSystem\\historiques\\userHistory";
	public static final String idOfUsers = Tools.DirecotryOfDocuments+"\\Applications\\applicationsData\\javaAppsData\\AccountManagementSystem\\settings\\idtool\\ids.txt";
	
	public static void writeUserToFileAsString(User user) {
		try {
			Tools.writeObjectAsStringToFile(user, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
	
	public static void writeIdToFile(User user) {
		try {
			Tools.writeStringToFile(user.getId()+"", idOfUsers);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Integer> getIDs(){
		ArrayList<String> idsAsString = Tools.getDataFromFile(idOfUsers);
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for(String str : idsAsString) 
		{
			ids.add(Integer.parseInt(str));
		}
		return ids;
	}
	
	public static ArrayList<String> getUsersFromFile()
	{
		return Tools.getDataFromFile(filePath);
	}
	
	public static ArrayList<User> getUsers()
	{
		ArrayList<String> uList = getUsersFromFile();
		ArrayList<User> users = new ArrayList<User>();
		
		for(int i = 0 ; i<uList.size() ;i++)
		{
			User user = new User();
			
			String arrayOfUsers [] = uList.get(i).split(", ");
			
			String id      = arrayOfUsers[0].substring(arrayOfUsers[0].indexOf("=")+1, arrayOfUsers[0].length());
			user.setId(Integer.parseInt(id));
			
			String codeUser= arrayOfUsers[1].substring(arrayOfUsers[1].indexOf("=")+1, arrayOfUsers[1].length());
			user.setCodeUser(codeUser);
			
			String firstName = arrayOfUsers[2].substring(arrayOfUsers[2].indexOf("=")+1, arrayOfUsers[2].length());
			user.setFirstName(firstName);
			
			String lastName = arrayOfUsers[3].substring(arrayOfUsers[3].indexOf("=")+1, arrayOfUsers[3].length());
			user.setLastName(lastName);
			
			String phoneNumber = arrayOfUsers[4].substring(arrayOfUsers[4].indexOf("=")+1, arrayOfUsers[4].length());
			user.setPhoneNumber(phoneNumber);
			
			String email = arrayOfUsers[3+2].substring(arrayOfUsers[3+2].indexOf("=")+1, arrayOfUsers[3+2].length());
			user.setEmail(email);
			
			String password = arrayOfUsers[3*2].substring(arrayOfUsers[3*2].indexOf("=")+1, arrayOfUsers[2*3].length());
			user.setPassword(password);
			
			String dateOfBirth = arrayOfUsers[3+4].substring(arrayOfUsers[7].indexOf("=")+1, arrayOfUsers[7].length());
			user.setDateOfBirth(dateOfBirth);
			
			String address = arrayOfUsers[8].substring(arrayOfUsers[8].indexOf("=")+1, arrayOfUsers[8].length());
			user.setAddress(address);
			
			String userType = arrayOfUsers[9].substring(arrayOfUsers[9].indexOf("=")+1, arrayOfUsers[9].length()-1);
			user.setUserType(userType);
			
			users.add(user);
		}
		
		return users;
	}
	
	public static User getOneUser(int id) {
		ArrayList<User> users = getUsers();
		User user = null;
		for(User u : users) 
		{
			if(u.getId() ==id) 
			{
				user = u;
				break;
			}
		}
		
		return user;
	}
	
	public static int getMaxID() {
		List<User> users = getUsers();
		User u =null;
		if(users==null || users.isEmpty()) {
			u = new User();
			u.setId(-2);
		}else {
			 u =users.stream().max(Comparator.comparing(User::getId)).orElseThrow(NoSuchElementException::new);
		}
	
		Random random =new Random();
		int rd = random.nextInt(999 -100+1)+100; 
		int some = rd + u.getId();
		while(getIDs().contains(some)) {
			rd = random.nextInt(999 -100+1)+100; 
			some = rd+u.getId();
		}
		
		return some;
	}
	
	public static User getUserByEmail(String email) {
		User user =getUsers().stream().filter(p->p.getEmail().equals(email)).findAny().orElse(null);
		return user;
	}
	
	public static String generateCodeUser() {
		String date = LocalDate.now()+"";
		date = date.replaceAll("-", "");
		Random rnd = new Random();
		int a = rnd.nextInt(9999 - 1000 + 1) + 1000;
		int b = rnd.nextInt(9999- 1000+1 ) +1000;
		return date+b+"-"+a;
	}
	
	public static void updateUser(ArrayList<User> users ,User user) throws IOException {
		//get the user we want to update 
		User c =users.stream().filter(a->a.getId()==user.getId()).findAny().orElseThrow(null);
		if(c==null) return;
		//clean the file that contains 
		Tools.cleanDataFromFile(filePath);
		//update the file
		users.remove(c);
		users.add(user);
		
		for(int i = 0 ; i<users.size();i++) {
			writeUserToFileAsString(users.get(i));
		}
		//System.out.println("done updating user");
	}
	
	public static void createHistoriqueForEveryUser(User user) {
		File historique = new File(userHistoryFilePath+"\\"+user.getCodeUser()+".txt");
		if(!historique.exists()) {
			try {
				historique.createNewFile();
				//System.out.println("created Successfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getUserHistoryNameFile(User user) {
		File file = new File(userHistoryFilePath);
		File[] listOfFiles = file.listFiles();
		if(user==null) return "none";
		for(int i = 0 ; i <listOfFiles.length;i++) {
			if(listOfFiles[i].getName().equals(user.getCodeUser()+".txt")) {
				return listOfFiles[i].getName();
			}
		}
		
		return "none";
	}
	
	public static void writeHistoryForUser(String history , User u) throws Exception {
		String userHistoryFile = getUserHistoryNameFile(u);
		//System.out.println(userHistoryFile);
		String his = "User [code user:"+u.getCodeUser() +", name:"+u.getFirstName()+" "+u.getLastName()+", history:"+history+" , date="+new Date()+""+"]";
		if(userHistoryFile.isEmpty()) return;
		Tools.writeStringToFile(his, userHistoryFilePath+"\\"+userHistoryFile);
	}
	
	public static ArrayList<String> getHistoryOfUser(User user){
		String file = getUserHistoryNameFile(user);
		ArrayList<String> history = Tools.getDataFromFile(userHistoryFilePath+"\\"+file);
		return history;
	}
	
	public static String deleteUser(User user,ArrayList<User> users) throws IOException {
		User c =users.stream().filter(a->a.getId()==user.getId()).findAny().orElseThrow(null);
		if(c==null) return "Failed, Couldn't find The User";
		//clean the file that contains 
		Tools.cleanDataFromFile(filePath);
		//remove the user from the files 
		users.remove(c);
		//update the users file
		for(int i = 0 ; i<users.size();i++) {
			writeUserToFileAsString(users.get(i));
		}
		//System.out.println("done updating user");
		return "deleted successfully";
	}

	
	
	
	
}
