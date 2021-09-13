package utils;

import java.util.ArrayList;
import java.util.Scanner;

import entities.Account;

public class AdminAccountMenus {
	public static int ListOfAccount() {
		Scanner scanner = new Scanner(System.in);
		int i = -1;
		ArrayList<Account> users = AccountTools.getAccounts();
		do {
			String leftAlignementFormat="  | %-4d | %-18s | %-21s | %-20s | %-12s | %-17s | %-16s  |                                                     |%n";
			System.out.println(Menus.header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                      List of Accounts                                                                                                                                 |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  | ID   | Account Code       | Date Of Creation      | Owner                | Solde        | Type Of Account   | Status Of Account |                                                     |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				
				for(Account a : users) 
				{
					String active ="";
					
					if(a.isActive()) {
						active ="Is Activated";
					}else {
						active ="Not Activated";
					}
					String fullName = UserTools.getOneUser(a.getUserID()).getFirstName()+" "+UserTools.getOneUser(a.getUserID()).getLastName();
					
					System.out.format(leftAlignementFormat, a.getId(),a.getAccountCode(),a.getDateOfCreation(),fullName,a.getSolde()+" DH",a.getTypeOfAccount(),active);
				}
			
			
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |       1: Add New Account | 2: Edit Account | 3: Delete Account | 4: Account Details | 5: Desactivate Account          | 0: Quit                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  Your choice :"); i = scanner.nextInt();
			Menus.clrscr();
		}while(i!= 0 && i!=1 && i!=2 && i!=3 && i!=4 && i!=5 );
		
		return i;
	}
	
	public static void main(String[] args) {
		System.out.println(ListOfAccount());
	}
}
