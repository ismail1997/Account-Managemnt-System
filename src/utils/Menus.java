package utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import entities.Account;
import entities.User;
import sessionManagement.SessionManagement;
import sessionManagement.SessionManagementTools;

public class Menus {
	
	public static String header=""

			+ "  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\r\n"
			+ "  |                                                    ______                                                             _                                                               |\r\n"
			+ "  |           /\\                               _      |  ___ \\                                                 _         | |              _                                               |\r\n"
			+ "  |          /  \\   ____ ____ ___  _   _ ____ | |_    | | _ | | ____ ____   ____  ____  ____ ____   ____ ____ | |_        \\ \\  _   _  ___| |_  ____ ____                                  |\r\n"
			+ "  |         / /\\ \\ / ___) ___) _ \\| | | |  _ \\|  _)   | || || |/ _  |  _ \\ / _  |/ _  |/ _  )    \\ / _  )  _ \\|  _)        \\ \\| | | |/___)  _)/ _  )    \\                                 |\r\n"
			+ "  |        | |__| ( (__( (__| |_| | |_| | | | | |__   | || || ( ( | | | | ( ( | ( ( | ( (/ /| | | ( (/ /| | | | |__    _____) ) |_| |___ | |_( (/ /| | | |                                |\r\n"
			+ "  |        |______|\\____)____)___/ \\____|_| |_|\\___)  |_||_||_|\\_||_|_| |_|\\_||_|\\_|| |\\____)_|_|_|\\____)_| |_|\\___)  (______/ \\__  (___/ \\___)____)_|_|_|                                |\r\n"
			+ "  |                                                                             (_____|                                       (____/                                                      |\n";
	
	
	public static void clrscr(){
	    try {
	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    } catch (IOException | InterruptedException ex) {}
	}
	
	public static int HomeMenu() {
		Scanner scanner = new Scanner(System.in);
		String leftFormatingString="  |                                             \t\t %-10s \t %-2d \t\t  \t\t \t\t\t\t                                          |%n";
		int i = -1;
		
		do {
			System.out.println(header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Login",1);
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Sign up",2);
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"About us",3);
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Quit",0);
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("                                                          Your choice :");i=scanner.nextInt();
			clrscr();
		}while(i!=0 && i!= 2 && i!= 1 && i!=3 && i!=4);
		scanner.nextLine(); 
		return i;
	}
	
	public static int LoginMenu() 
	{
		//get existed users 
		ArrayList<User> users = UserTools.getUsers();
		
		
		
		Scanner scanner = new Scanner(System.in);
		String leftFormatingString="  |                                             \t %-20s \t    \t\t                                                                                          |%n";
		int i = -1;
		int attempt = 0 ;
		boolean wrongCredentials = false;
		boolean emailLenght = false;
		do {
			System.out.format(header);
			if(wrongCredentials) {
				System.out.println("  |                                                                                                                                                                                       |");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |                                       +------------------------------------------------+                                                                                              |%n");
				System.out.format("  |                                       |       Sorry , wrong cridentials  try again     |                                                                                              |%n");
				System.out.format("  |                                       +------------------------------------------------+                                                                                              |%n");
				attempt ++;
				
			}
			
			if(emailLenght) {
				System.out.println(" |                                                                                                                                                                                       |");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |                                       +------------------------------------------------+                                                                                              |%n");
				System.out.format("  |                                       | Please Enter a valid email with size <25 chars |                                                                                              |%n");
				System.out.format("  |                                       +------------------------------------------------+                                                                                              |%n");
			}
			System.out.println("  |                                                                                                                                                                                       |");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                                                                                                                                       |%n");
						System.out.format(leftFormatingString,"Enter your email :");
						System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  |                                             ->: ");
			String email=scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
						System.out.format(leftFormatingString,"Enter your password :");
						System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  |                                             ->: ");
			String password=scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			
			User us = UserTools.getUserByEmail(email);
			boolean d=false;
			boolean c =false;
			
			if(us==null) {
				d=false;
				c=false;
			}else {
				d=true;
				if(!us.getPassword().equals(password)) {
					c=false;
				}else {
					c=true;
				}
			}
			
			
			if(email.length()>25) {
				emailLenght=true;
			}
			if(!d) {
				wrongCredentials = true;
			}
			if(!c) {
				wrongCredentials = true;
			}
			
			if(d && c) {
				i=1;
				User u = UserTools.getUserByEmail(email);
				SessionManagement s = new SessionManagement((int)Math.random(),u , new Date()+"");
				try {
					SessionManagementTools.startSession(s);
					String history ="Logged in successfully";
					UserTools.writeHistoryForUser(history, u);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(u.getUserType().equals("admin")) {
					i=1;
				}else{
					i=11;
				}
				
			}
			if(attempt ==2) {
				i = 0;
			}
			clrscr();
			//System.out.println(password+email);
		}while(i!=1 && i!=0 && i!=11);
		
		
		return i;
	}
	public static int singUpUser() throws Exception {
		
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
				AdminMenus.emailAlreadyExist();
			}
			if(emailLenghtExcedded) {
				AdminMenus.emailLengthExcedded();
			}
			if(fieldIsEmpty) {
				AdminMenus.fieldIsEmpty();
			}
			
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                              Sign Up                                                                                                  |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                                                                                                                                       |%n");
			if(emailAlreaydExist || emailLenghtExcedded || fieldIsEmpty) scanner.nextLine();
			System.out.format(leftFormatingString,"Enter Your first Name");String firstName = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter Your last Name");String lastName = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter Your Email");String email = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter Your Phone Number");String phoneNumber = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter Your Date Of Birth");String dateOfBirth = scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter Your of Address");String address= scanner.nextLine();
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format(leftFormatingString,"Enter Your Password");String password=scanner.nextLine();
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
				
				
				Account account = new Account();
				account.setActive(false);
				account.setSolde(100.0);
				account.setDateOfCreation(LocalDate.now()+"");
				account.setUserID(user.getId());
				account.setAccountCode(AccountTools.generateAccountCode());
				account.setTypeOfAccount("Single Account");
				account.setId(AccountTools.getMaxID()+1);
				
				UserTools.createHistoriqueForEveryUser(user);
				AccountTools.createHistoriqueForEveryAccount(account);
				AccountTools.writeAccountToFileAsString(account);
				
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
			String history ="Profile and Account created successfully";
			UserTools.writeHistoryForUser(history, user);
			profileCreatedSuccessfully();
		}
		return i;
	}

	
	public static void profileCreatedSuccessfully() throws InterruptedException {
		System.out.println(Menus.header);
		System.out.println("  |                                                                                                                                                                                       |");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  |                                       |       Gongratulation, Your account has been created Successfully     |                                                                        |%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		TimeUnit.MILLISECONDS.sleep(2500);
		clrscr();
	}
	
	public static int withDrawMoneyMenu(User user) {
		Scanner scanner = new Scanner ( System.in);
		boolean doesntSelectValideAccount = false;
		boolean errorWithdrawing = false;
		int attempt = 0;
		int i = -1 ;
		String leftFormatingString="  |                          %-20s :\t %-50s                                                                               |%n"; 
		ArrayList<Integer> idOfAccounts = new ArrayList<Integer>();
		do {
			System.out.println(Menus.header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  +                               Please select account id that you want to withdraw the money of                                                                                         +%n");
			
			if(doesntSelectValideAccount) {
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |                       !!  you didn't select a valid account, try again                                                                                                                |%n");
				attempt++;
			}else if(errorWithdrawing) {
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |              Error Withdrawing Try again                                                                                                                                              |%n");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				attempt++;
			}
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |              User Accounts                                                                                                                                                            |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			List<Account> getAccountsByUser=AccountTools.getAccountOfUser(user.getId());
			for(int j =0;j<getAccountsByUser.size();j++) {
				idOfAccounts.add(getAccountsByUser.get(j).getId());
				System.out.format("  |                   Account : "+j+"                                                                                                                                                         |%n");
				System.out.format(leftFormatingString,"Account Code",getAccountsByUser.get(j).getAccountCode());
				System.out.format(leftFormatingString,"Account ID",getAccountsByUser.get(j).getId());
				System.out.format(leftFormatingString,"Solde ",getAccountsByUser.get(j).getSolde()+" DH");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			}
			System.out.print("  |    Your Choice :"); int u = scanner.nextInt();
			
			if(!idOfAccounts.contains(u)) {
				i=-2;
				doesntSelectValideAccount = true;
			}else {
				doesntSelectValideAccount = false;
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |              You have selected the account "+AccountTools.getOneAccountById(u).getAccountCode()+"                                                                                                                          |%n");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.print("  |  Type the ammount of money :");double money = scanner.nextDouble();
				boolean didOrNotWithdraw = AccountTools.withdrawMoney(AccountTools.getAccounts(), AccountTools.getOneAccountById(u), money);
				//System.out.println(didOrNotWithdraw);
				if(didOrNotWithdraw) {
					i=1;
					try {
						WithdrawMoneySuccessfully();
						String history = "Withdraw "+money+" DHS from his account "+AccountTools.getOneAccountById(u).getAccountCode();
						UserTools.writeHistoryForUser(history, user);
						AccountTools.writeHistoryForAccount("Account has been debited", AccountTools.getOneAccountById(u));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					errorWithdrawing = true;
					i=-1;
				}
			}
			if(attempt == 2) {
				i = 0;
			}
			
			
			clrscr();
		}while(i!=1 && i!=0);
		return i;
	}
	
	public static int depositMoneyMenu(User user) {
		Scanner scanner = new Scanner ( System.in);
		boolean doesntSelectValideAccount = false;
		boolean errorDeopositing = false;
		int attempt = 0;
		int i = -1 ;
		String leftFormatingString="  |                          %-20s :\t %-50s                                                                               |%n"; 
		ArrayList<Integer> idOfAccounts = new ArrayList<Integer>();
		do {
			System.out.println(Menus.header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  +                               Please select account id that you want to deposit the money to                                                                                          +%n");
			
			if(doesntSelectValideAccount) {
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |                       !!  you didn't select a valid account, try again                                                                                                                |%n");
				attempt++;
			}else if(errorDeopositing) {
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |               Error Depositing Try again                                                                                                                                              |%n");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				attempt++;
			}
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |              User Accounts                                                                                                                                                            |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			List<Account> getAccountsByUser=AccountTools.getAccountOfUser(user.getId());
			for(int j =0;j<getAccountsByUser.size();j++) {
				idOfAccounts.add(getAccountsByUser.get(j).getId());
				System.out.format("  |                   Account : "+j+"                                                                                                                                                         |%n");
				System.out.format(leftFormatingString,"Account Code",getAccountsByUser.get(j).getAccountCode());
				System.out.format(leftFormatingString,"Account ID",getAccountsByUser.get(j).getId());
				System.out.format(leftFormatingString,"Solde ",getAccountsByUser.get(j).getSolde()+" DH");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			}
			System.out.print("  |    Your Choice :"); int u = scanner.nextInt();
			
			if(!idOfAccounts.contains(u)) {
				i=-2;
				doesntSelectValideAccount = true;
			}else {
				doesntSelectValideAccount = false;
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  |              You have selected the account "+AccountTools.getOneAccountById(u).getAccountCode()+"                                                                                                                          |%n");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.print("  |  Type the ammount of money :");double money = scanner.nextDouble();
				boolean didOrNotDeposit = AccountTools.depositMoney(AccountTools.getAccounts(), AccountTools.getOneAccountById(u), money);
				//System.out.println(didOrNotWithdraw);
				if(didOrNotDeposit) {
					i=1;
					try {
						depositMoneySuccessfully();
						String history = "Deposit "+money+" DHS to his account "+AccountTools.getOneAccountById(u).getAccountCode();
						UserTools.writeHistoryForUser(history, user);
						AccountTools.writeHistoryForAccount("Account has been credited "+money+" DHs, Solde :"+AccountTools.getOneAccountById(u).getSolde(),AccountTools.getOneAccountById(u));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					errorDeopositing = true;
					i=-1;
				}
			}
			if(attempt == 2) {
				i = 0;
			}
			
			
			clrscr();
		}while(i!=1 && i!=0);
		return i;
	}
	public static void WithdrawMoneySuccessfully() throws InterruptedException {
		clrscr();
		System.out.println(Menus.header);
		System.out.println("  |                                                                                                                                                                                       |");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  |                                       |  Withdrawal operation has been executed successfully                 |                                                                        |%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		TimeUnit.MILLISECONDS.sleep(2500);
		clrscr();
	}
	
	public static void depositMoneySuccessfully() throws InterruptedException {
		clrscr();
		System.out.println(Menus.header);
		System.out.println("  |                                                                                                                                                                                       |");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  |                                       |     Deposit operation has been executed successfully                 |                                                                        |%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		TimeUnit.MILLISECONDS.sleep(2500);
		clrscr();
	}
	
	
	public static void aboutUsMenu() {
		Scanner scanner= new Scanner(System.in);
		int i = -1;
		while(i!=0) {
			System.out.println(Menus.header);
			System.out.println("  |                                                                                                                                                                                       |");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  +                                                                                                                                                                                       +%n");
			System.out.format("  +                                           As the number of software tools we use to run our businesses continues to grow,                                                             +%n");
			System.out.format("  +                                       we’re generating more and more data in more and more places. At Account System Management                                                       +%n");
			System.out.format("  +                our mission is to make it as easy as possible for everyone in a company to monitor, analyze, and improve performance in one spot on its banking system.                +%n");
			System.out.format("  +                                                                                                                                                                                       +%n");
			System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
			System.out.format("  |                                       |     Your banking operations is now safer and more reliable           |                                                                        |%n");
			System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
			System.out.format("  +                                                                                                                                                                                       +%n");
			System.out.format("  +                                                                                                                                                                                       +%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  +                Creator : Ismail Bouaddi                                                         Copyright : @2021                                                                     +%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.print("  |        To go back tape 0  : "); i = scanner.nextInt();
			clrscr();
		}
	}
	
	public static int myHistory(User user) {
		Scanner scanner = new Scanner(System.in);
		String leftFormatingString="  |    %-165s              |%n";
		ArrayList<String> history = UserTools.getHistoryOfUser(user);
		int i = -1;
		
		do {
			System.out.println(header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                               User History                                                                                                            |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                                                                                                                                       |%n");
			if(history.size()<=25) {
				history.forEach(hist->System.out.format(leftFormatingString,hist));
			}else {
				for (int d = history.size()-1 ; d>(history.size()-25) ; d--) {
					System.out.format(leftFormatingString,history.get(d));
				}
			}
			
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |       Tape 0 to go back :");i=scanner.nextInt();
			clrscr();
		}while(i!=0 );
		scanner.nextLine(); 
		return i;
	}
	
	public static int myAccountHistory(int userID) {
		Scanner scanner = new Scanner(System.in);
		String leftFormatingString="  |    %-165s              |%n";
		
		ArrayList<String> history = new ArrayList<String>();
		history=AccountTools.getHistoryForAllAccounts(AccountTools.getAccountsByUserID(userID));
		if(history == null) {
			history = new ArrayList<String>();
			history .add("Account None Exist");
		}
		int i = -1;
		
		do {
			System.out.println(header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                               Account History                                                                                                         |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                                                                                                                                                                       |%n");
			for(int r = 0 ; r<history.size();r++) {
				System.out.format(leftFormatingString,history.get(r));
			}
			System.out.format("  |                                                                                                                                                                                       |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |       Tape 0 to go back :");i=scanner.nextInt();
			clrscr();
		}while(i!=0 );
		scanner.nextLine(); 
		return i;
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(myAccountHistory(1));
		//System.out.println(AccountTools.getOneAccountById(13));
	}
	
	
	
}
