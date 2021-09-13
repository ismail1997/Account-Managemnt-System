package utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entities.Account;
import entities.User;
import sessionManagement.SessionManagement;
import sessionManagement.SessionManagementTools;

public class UserMenus {
	
	
	public static int userMenu() {
		
		int alpha = 0;
		Scanner scanner =new Scanner(System.in);
		String leftFormatingString="  |                                                     %-2d :\t %-25s                                                                                                |%n"; 
		do {
			System.out.println(Menus.header);
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                   User  Menu                                                                                                          |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,1,"My Profile");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,2,"My Accounts");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,3,"My History");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,4,"Withdraw Money");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,5,"Deposit Money");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,6,"Account History");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,7,"Reclam To Admin");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,0,"Quit");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		    System.out.print("  |          Your choice : ");alpha = scanner.nextInt();
		    Menus.clrscr();
		}while(alpha != 0 && alpha != 1 && alpha != 2 && alpha != 3 && alpha != 4 && alpha != 5 && alpha != 6 );
		return alpha;
	}
	
	public static int userProfile(User user) {
		Scanner scanner = new Scanner(System.in) ;
		String leftFormatingString="  |                          %-20s :\t %-50s                                                                               |%n"; 
		int r = -1;
		do {
			System.out.println(Menus.header);
			System.out.println("  |                                                                                                                                                                                       |");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                       |                           User Profile                              |                                                                         |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |              User Details                                                                                                                                                             |%n");
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
			System.out.format("  |              User Accounts                                                                                                                                                            |%n");
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
			System.out.format("  |           1:) Edit Profile         0:) Back                                                                                                                                           |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("                        Your choice : ");r =scanner.nextInt();
			Menus.clrscr();
		}while(r!=1 && r!= 0);
		
		
		return r;
	}
	
	public static int userAccounts(User user) {
		Scanner scanner = new Scanner(System.in) ;
		String leftFormatingString="  |                          %-20s :\t %-50s                                                                               |%n"; 
		int r = -1;
		do {
			System.out.println(Menus.header);
			System.out.println("  |                                                                                                                                                                                       |");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                       |                           User Account                              |                                                                         |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |              User Accounts                                                                                                                                                            |%n");
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
			System.out.format("  |           1:) Withdraw Money        2:) Deposit Money          3:) Desactivate Account       0:) Back                                                                                 |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("                        Your choice : ");r =scanner.nextInt();
			Menus.clrscr();
		}while(r!=1 && r!= 2 && r!= 0 && r!= 3);
		
		
		return r;
	}
	
	public static int editProfile(User user) {
		Scanner scanner = new Scanner(System.in) ;
		String leftFormatingString="  | %-25s  :"; 
		String lft="  |   %-13s: %-40s"; 
		int r = -1;
		String firstName ="";String lastName="";
		String email =""; String phoneNumber="";
		String dateOfBirth="";String address ="";
		String password="";
		while(r!=1 && r!= 2 && r!= 0) {
			System.out.println(Menus.header);
			System.out.println("  |                                                                                                                                                                                       |");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                       |                          Edit User Profile                             |                                                                      |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |              To edit a field tape something , if not just left it empty and it will save the oldest value                                                                             |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.println("  |                                                                                                                                                                                       |");
			System.out.format(lft,"First Name",user.getFirstName());System.out.format(leftFormatingString,"Enter new first Name"); firstName = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(lft,"Last Name",user.getLastName());System.out.format(leftFormatingString,"Enter new last Name"); lastName = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(lft,"Email",user.getEmail());System.out.format(leftFormatingString,"Enter new Email"); email = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(lft,"Phone Number",user.getPhoneNumber());System.out.format(leftFormatingString,"Enter new Phone Number"); phoneNumber = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(lft,"Date Of Birth",user.getDateOfBirth());System.out.format(leftFormatingString,"Enter new Date Of Birth"); dateOfBirth = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(lft,"Address",user.getAddress());System.out.format(leftFormatingString,"Enter new of Address"); address= scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(lft,"Password",user.getPassword());System.out.format(leftFormatingString,"Enter new Password"); password=scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |           1:) confirm         2:) abort          0:) back                                                                                                                             |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("                        Your choice : ");r =scanner.nextInt();
			Menus.clrscr();
		};
		
		if(r == 1 ) {
			
			if(firstName =="" ||firstName.isEmpty()) {
				firstName = user.getFirstName();
			}else {
				user.setFirstName(firstName);
			}
			
			if(lastName=="" || lastName.isEmpty()) {
				lastName=user.getLastName();
			}else {
				user.setLastName(lastName);
			}
			
			if(email=="" || email.isEmpty()) {
				email= user.getEmail();
			}else {
				user.setEmail(email);
			}
			
			if(phoneNumber=="" || phoneNumber.isEmpty()) {
				phoneNumber=user.getPhoneNumber();
			}else {
				user.setPhoneNumber(phoneNumber);
			}
			
			if(dateOfBirth=="" || dateOfBirth.isEmpty()) {
				dateOfBirth=user.getDateOfBirth();
			}else {
				user.setDateOfBirth(dateOfBirth);
			}
			
			
			if(password=="" || password.isEmpty()) {
				password=user.getPassword();
			}else {
				user.setPassword(password);
			}
			
			if(address=="" || address.isEmpty()) {
				address=user.getAddress();
			}else {
				user.setAddress(address);
			}
			
			try {
				if(address=="" && password=="" && dateOfBirth=="" && firstName=="" && lastName=="" && email=="" && phoneNumber=="") {
					r=0;
				}else {
					UserTools.updateUser(UserTools.getUsers(), user);
					SessionManagement session = new SessionManagement((int)Math.random(), user, new Date()+"");
					SessionManagementTools.startSession(session);
					UserTools.writeHistoryForUser("Updated his profile", user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println(user);
		return r;
	}
	
	public static void main(String[] args) throws Exception {
		User user = SessionManagementTools.getSession().getSessionUser();
		user.setUserType("admin");
		UserTools.updateUser(UserTools.getUsers(), user);
		//System.out.println(editProfile(user));
		SessionManagementTools.startSession(new SessionManagement((int)Math.random(),user, new Date()+""));
		UserTools.getUsers().forEach(System.out::println);;
	}
}
