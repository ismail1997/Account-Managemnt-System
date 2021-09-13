package utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Account;
import entities.User;
import sessionManagement.SessionManagementTools;

public class AdminMenus {
	public static int AdminMenu() {
		
		int alpha = 0;
		Scanner scanner =new Scanner(System.in);
		String leftFormatingString="  |                                                     %-2d :\t %-25s                                                                                                |%n"; 
		do {
			System.out.println(Menus.header);
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                   Admin Menu                                                                                                          |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,1,"List of Users");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,2,"Liste of Accounts");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,3,"My Profile");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,4,"My Accounts");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,5,"Statistics");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,6,"My History");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,7,"My Account History");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,8,"System History");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,0,"Quit");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		    System.out.print("  |          Your choice : ");alpha = scanner.nextInt();
		    Menus.clrscr();
		}while(alpha != 0 && alpha != 1 && alpha != 2 && alpha != 3 && alpha != 4 && alpha != 5 && alpha != 6 && alpha != 7 && alpha != 8) ;
		return alpha;
	}
	
	public static int ListOfUsers() {
		Scanner scanner = new Scanner(System.in);
		int i = -1;
		ArrayList<User> users = UserTools.getUsers();
		do {
			String leftAlignementFormat="  | %-4d | %-16s | %-15s | %-15s | %-25s | %-15s | %-14s | %-49s       |%n";
			System.out.println(Menus.header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                      List of Users                                                                                                                                    |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  | ID   | User Code         | First Name      | Last Name       | Email                     | Phone Number    | Date Of Birth  | Address                                                 |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				
				for(User u : users) 
				{
					System.out.format(leftAlignementFormat,u.getId(),u.getCodeUser(),u.getFirstName(),u.getLastName(),u.getEmail(),u.getPhoneNumber(),u.getDateOfBirth(),u.getAddress());
				}
			
			
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |       1: Add New User | 2: Edit User | 3: Delete User | 4: User Details | 5: Back          | 0: Quit                                                                                  |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  Your choice :"); i = scanner.nextInt();
			Menus.clrscr();
		}while(i!= 0 && i!=1 && i!=2 && i!=3 && i!=4 && i!=5 );
		
		return i;
	}
	public static int searchUser(User user) {
		int i = 0;
		Scanner scanner = new Scanner(System.in);
		String leftFormatingString="                        |\t %-25s : "; 
		System.out.println(Menus.header);
		System.out.format("                        +--------------------------------------------------------------------+%n");
		System.out.format("                        |                        SEARCH FOR USER                             |%n");
		System.out.format("                        +--------------------------------------------------------------------+%n");
		System.out.format("                        |                                                                    |%n");
		System.out.format(leftFormatingString,"Enter User Email");String email = scanner.nextLine();
		System.out.format("                        |                                                                    |%n");
		System.out.format("                        +--------------------------------------------------------------------+%n");
		System.out.format("                        | 1:) confirm        2:) abort          0:) back                     |%n");
		System.out.format("                        +--------------------------------------------------------------------+%n");
		System.out.format("                        Your choice : "); i =scanner.nextInt();
		return i;
	}
	public static int addNewUser() throws Exception {
		
		ArrayList<User> users = UserTools.getUsers();
		boolean emailAlreaydExist = false;
		boolean emailLenghtExcedded=false;
		boolean fieldIsEmpty = false;
		
		
		Scanner scanner = new Scanner(System.in);
		String leftFormatingString="  |                                              \t %-25s : "; 
		int attempt = 0 ;
		int i=0;
		User user = null;
		do {
			System.out.println(Menus.header);
			
			if(emailAlreaydExist) {
				emailAlreadyExist();
			}
			if(emailLenghtExcedded) {
				emailLengthExcedded();
			}
			if(fieldIsEmpty) {
				fieldIsEmpty();
			}
			
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                              Add New User                                                                                             |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                                                                                                                                       |%n");
			if(emailAlreaydExist || emailLenghtExcedded || fieldIsEmpty) scanner.nextLine();
			System.out.format(leftFormatingString,"Enter User first Name");String firstName = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter User last Name");String lastName = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter User Email");String email = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter User Phone Number");String phoneNumber = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter User Date Of Birth");String dateOfBirth = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter Address of User");String address= scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter User Password");String password=scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |           1:) confirm         2:) abort          0:) back                                                                                                                             |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("                        Your choice : ");i =scanner.nextInt();

			emailAlreaydExist = users.stream().anyMatch(use->use.getEmail().equals(email));
			emailLenghtExcedded = (email.length()>25) ? true : false;
			
			if(firstName.equals("") || lastName.equals("") || email.equals("") || phoneNumber.equals("") || dateOfBirth.equals("")|| address.equals("") || password.equals(""))
			{
				fieldIsEmpty = true;
				attempt ++;
			}else {
				fieldIsEmpty = false;
			}
			
			System.out.println(fieldIsEmpty);
			if(!emailAlreaydExist && !emailLenghtExcedded && !fieldIsEmpty) {
				user = new User();
				user.setId(UserTools.getMaxID()+1);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setPassword(password);
				user.setEmail(email);
				user.setAddress(address.replace(',', ';'));
				user.setDateOfBirth(dateOfBirth);
				user.setPhoneNumber(phoneNumber);
				user.setCodeUser(UserTools.generateCodeUser());
				user.setUserType("simple");
				//System.out.println(user);
				i=1;
			}
			if(i==1 && ( emailAlreaydExist| emailLenghtExcedded | fieldIsEmpty) ) {
				i=-1;
			}
			if(attempt == 3) {
				i=0;
			}
			Menus.clrscr();
		} while( i!=0 && i!=1);
		if(i==1) {
			UserTools.writeUserToFileAsString(user);
			UserTools.writeHistoryForUser("Created a new User "+user.getEmail(), SessionManagementTools.getSession().getSessionUser());
			UserTools.createHistoriqueForEveryUser(user);
			
			Account account = new Account();
			account.setActive(false);
			account.setSolde(100.0);
			account.setDateOfCreation(LocalDate.now()+"");
			account.setUserID(user.getId());
			account.setAccountCode(AccountTools.generateAccountCode());
			account.setTypeOfAccount("Single Account");
			account.setId(AccountTools.getMaxID()+1);
			
			AccountTools.createHistoriqueForEveryAccount(account);
		}
		return i;
	}
	
	public static int AdminProfile(User user) {
		Scanner scanner = new Scanner(System.in) ;
		String leftFormatingString="  |                          %-20s :\t %-50s                                                                               |%n"; 
		int r = -1;
		do {
			System.out.println(Menus.header);
			System.out.println("  |                                                                                                                                                                                       |");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                       |                           Admin Profile                              |                                                                        |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |              Admin Details                                                                                                                                                            |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format(leftFormatingString,"ID",user.getId());
			System.out.format(leftFormatingString,"Code User",user.getCodeUser());
			System.out.format(leftFormatingString,"First Name",user.getFirstName());
			System.out.format(leftFormatingString,"Last Name",user.getLastName());
			System.out.format(leftFormatingString,"Email",user.getEmail());
			System.out.format(leftFormatingString,"Phone Number",user.getPhoneNumber());
			System.out.format(leftFormatingString,"Date Of Birth",user.getDateOfBirth());
			System.out.format(leftFormatingString,"Address",user.getAddress());
			System.out.format(leftFormatingString,"Type Of Profile",user.getUserType());
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |              Admin Accounts                                                                                                                                                           |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			List<Account> getAccountsByUser=AccountTools.getAccountOfUser(user.getId());
			
			for(int i =0;i<getAccountsByUser.size();i++) {
				System.out.format("  |                   Account : "+i+"                                                                                                                                                         |%n");
				System.out.format(leftFormatingString,"Account Code",getAccountsByUser.get(i).getAccountCode());
				System.out.format(leftFormatingString,"Type Of Account",getAccountsByUser.get(i).getTypeOfAccount());
				System.out.format(leftFormatingString,"Solde ",getAccountsByUser.get(i).getSolde()+" DH");
				System.out.format(leftFormatingString,"Date Of Creation",getAccountsByUser.get(i).getDateOfCreation());
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			}
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |           1:) Edit Profile                 0:) Back                                                                                                                                   |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("                        Your choice : ");r =scanner.nextInt();
			Menus.clrscr();
		}while(r!=1  && r!= 0);
		
		
		return r;
	}
	
	
	
	
	
	
	public static void emailAlreadyExist() {
		System.out.println("  |                                                                                                                                                                                       |");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  |                                       |       Sorry , Email already in use please try with another email     |                                                                        |%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
	}
	
	public static void emailLengthExcedded() {
		System.out.println("  |                                                                                                                                                                                       |");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  |                                       |       Sorry , Email length should be less than 25 characters         |                                                                        |%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
	}
	
	public static void fieldIsEmpty() {
		System.out.println("  |                                                                                                                                                                                       |");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  |                                       |       Sorry , It seems that one or two field are empty               |                                                                        |%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
	}
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(AdminProfile(UserTools.getOneUser(1)));
		//User user = new User(45, null, null, null, null, null, null, null, null, null);
		//Tools.writeObjectAsStringToFile(user,"src/textfiles/persons.txt");
	}
	
}
