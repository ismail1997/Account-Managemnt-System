package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Account;
import entities.User;
import sessionManagement.SessionManagementTools;

public class AdminAccountMenus {
	public static int ListOfAccount() {
		Scanner scanner = new Scanner(System.in);
		int i = -1;
		ArrayList<Account> accounts = AccountTools.getAccounts();
		ArrayList<User>  users =UserTools.getUsers(); 
		List<Integer> idOfUsers = users.stream().map(User::getId).collect(Collectors.toList());
		do {
			String leftAlignementFormat="  | %-4d | %-18s | %-21s | %-20s | %-12s | %-17s | %-16s  |                                                     |%n";
			System.out.println(Menus.header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                      List of Accounts                                                                                                                                 |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  | ID   | Account Code       | Date Of Creation      | Owner                | Solde        | Type Of Account   | Status Of Account |                                                     |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				
				for(Account a : accounts) 
				{
					if(idOfUsers.contains(a.getUserID())) {
						String active ="";
						
						if(a.isActive()) {
							active ="Is Activated";
						}else {
							active ="Not Activated";
						}
						String fullName = UserTools.getOneUser(a.getUserID()).getFirstName()+" "+UserTools.getOneUser(a.getUserID()).getLastName();
						
						System.out.format(leftAlignementFormat, a.getId(),a.getAccountCode(),a.getDateOfCreation(),fullName,a.getSolde()+" DH",a.getTypeOfAccount(),active);
					}
					
					
				}
			
			
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |       1: Activate Account  | 2: Desactivate Account          | 0: Quit                                                                                                                |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  Your choice :"); i = scanner.nextInt();
			Menus.clrscr();
		}while(i!= 0 && i!=1 && i!=2 && i!=3);
		
		return i;
	}
	
	public static int activateAccountMenu() throws Exception {
		Scanner scanner = new Scanner(System.in);
		int i = -1;
		int choice = -1;
		int attempts = 0;
		boolean didntSelectValidAccount = false;
		ArrayList<Account> accounts = AccountTools.getAccounts();
		ArrayList<User>  users =UserTools.getUsers(); 
		List<Integer> idOfUsers = users.stream().map(User::getId).collect(Collectors.toList());
		
		do {
			List<Integer> idOfAccounts = new ArrayList<Integer>();
			String leftAlignementFormat="  | %-4d | %-18s | %-21s | %-20s | %-12s | %-17s | %-16s  |                                                     |%n";
			System.out.println(Menus.header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                      List of Accounts Not Active                                                                                                                      |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			if(didntSelectValidAccount) {
				System.out.format("  |                                      You didn't select a valid account, try again                                                                                                     |%n");
			}
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  | ID   | Account Code       | Date Of Creation      | Owner                | Solde        | Type Of Account   | Status Of Account |                                                     |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				
				for(Account a : accounts) 
				{
					if(idOfUsers.contains(a.getUserID()) && !a.isActive()) {
						String active ="";
						idOfAccounts.add(a.getId());
						if(a.isActive()) {
							active ="Is Activated";
						}else {
							active ="Not Activated";
						}
						String fullName = UserTools.getOneUser(a.getUserID()).getFirstName()+" "+UserTools.getOneUser(a.getUserID()).getLastName();
						
						System.out.format(leftAlignementFormat, a.getId(),a.getAccountCode(),a.getDateOfCreation(),fullName,a.getSolde()+" DH",a.getTypeOfAccount(),active);
					}
					
					
				}
			
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |     Please select id of accounts you wanna activate :");choice = scanner.nextInt();
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			if(!idOfAccounts.contains(choice)) {
				attempts++;
				didntSelectValidAccount=true;
			}else {
				didntSelectValidAccount=false;
			}
			
			if(didntSelectValidAccount) {
				i=-1;
			}else {
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |                 You did select this account : "+AccountTools.getOneAccountById(choice).getAccountCode()+"                                                                                                                       |%n");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |       1 : confirm            0 : abort                                                                                                                                                |%n");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  Your choice :"); i = scanner.nextInt();
			}
			
			if(i==1) {
				Account account = AccountTools.getOneAccountById(choice);
				account.setActive(true);
				AccountTools.updateAccount(AccountTools.getAccounts(), account);
				UserTools.writeHistoryForUser("Has activated this account "+account.getAccountCode(), SessionManagementTools.getSession().getSessionUser());
			}
			if(attempts==3 && didntSelectValidAccount==true) {
				i=0;
			}
			
			Menus.clrscr();
		}while(i!= 0 && i!=1  );
		
		return i;
	}
	
	public static int desactivateAccountMenu() throws Exception {
		Scanner scanner = new Scanner(System.in);
		int i = -1;
		int choice = -1;
		int attempts = 0;
		boolean didntSelectValidAccount = false;
		ArrayList<Account> accounts = AccountTools.getAccounts();
		ArrayList<User>  users =UserTools.getUsers(); 
		List<Integer> idOfUsers = users.stream().map(User::getId).collect(Collectors.toList());
		
		do {
			List<Integer> idOfAccounts = new ArrayList<Integer>();
			String leftAlignementFormat="  | %-4d | %-18s | %-21s | %-20s | %-12s | %-17s | %-16s  |                                                     |%n";
			System.out.println(Menus.header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                      List of Active Accounts                                                                                                                          |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			if(didntSelectValidAccount) {
				System.out.format("  |                                      You didn't select a valid account, try again                                                                                                     |%n");
			}
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  | ID   | Account Code       | Date Of Creation      | Owner                | Solde        | Type Of Account   | Status Of Account |                                                     |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				
				for(Account a : accounts) 
				{
					if(idOfUsers.contains(a.getUserID()) && a.isActive()) {
						String active ="";
						idOfAccounts.add(a.getId());
						if(a.isActive()) {
							active ="Is Activated";
						}else {
							active ="Not Activated";
						}
						String fullName = UserTools.getOneUser(a.getUserID()).getFirstName()+" "+UserTools.getOneUser(a.getUserID()).getLastName();
						
						System.out.format(leftAlignementFormat, a.getId(),a.getAccountCode(),a.getDateOfCreation(),fullName,a.getSolde()+" DH",a.getTypeOfAccount(),active);
					}
					
					
				}
			
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |     Please select id of accounts you wanna desactivate :");choice = scanner.nextInt();
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			if(!idOfAccounts.contains(choice)) {
				attempts++;
				didntSelectValidAccount=true;
			}else {
				didntSelectValidAccount=false;
			}
			
			if(didntSelectValidAccount) {
				i=-1;
			}else {
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |                 You did select this account : "+AccountTools.getOneAccountById(choice).getAccountCode()+"                                                                                                                       |%n");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |       1 : confirm            0 : abort                                                                                                                                                |%n");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  Your choice :"); i = scanner.nextInt();
			}
			
			if(i==1) {
				Account account = AccountTools.getOneAccountById(choice);
				account.setActive(false);
				AccountTools.updateAccount(AccountTools.getAccounts(), account);
				UserTools.writeHistoryForUser("Has desactivated this account "+account.getAccountCode(), SessionManagementTools.getSession().getSessionUser());
			}
			if(attempts==3 && didntSelectValidAccount==true) {
				i=0;
			}
			
			Menus.clrscr();
		}while(i!= 0 && i!=1  );
		
		return i;
	}

}
