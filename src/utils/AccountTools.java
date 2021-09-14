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
import java.util.stream.Collector;
import java.util.stream.Collectors;

import entities.Account;
import entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class AccountTools {
	public static String filePath ="C:\\Users\\ismail\\Documents\\Applications\\applicationsData\\javaAppsData\\AccountManagementSystem\\accounts\\accounts.txt";
	private static String accountHistoryFilePath="C:\\Users\\ismail\\Documents\\Applications\\applicationsData\\javaAppsData\\AccountManagementSystem\\historiques\\accountHistory";

	
	public static void writeAccountToFileAsString(Account account) 
	{
		try {
			Tools.writeObjectAsStringToFile(account, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getAccountsFromFile()
	{
		return Tools.getDataFromFile(filePath);
	}
	
	public static ArrayList<Account> getAccounts()
	{
		ArrayList<String> accountList = getAccountsFromFile();
		ArrayList<Account> accounts = new ArrayList<>();
		
		for(int i = 0 ; i<accountList.size() ;i++)
		{
			Account account= new Account();
			
			String arrayOfAccount [] = accountList.get(i).split(", ");
			
			String id      = arrayOfAccount[0].substring(arrayOfAccount[0].indexOf("=")+1, arrayOfAccount[0].length());
			account.setId(Integer.parseInt(id));
			
			String accountCode= arrayOfAccount[1].substring(arrayOfAccount[1].indexOf("=")+1, arrayOfAccount[1].length());
			account.setAccountCode(accountCode);
			
			String dateOfCreation = arrayOfAccount[2].substring(arrayOfAccount[2].indexOf("=")+1, arrayOfAccount[2].length());
			account.setDateOfCreation(dateOfCreation);
			
			String userID = arrayOfAccount[3].substring(arrayOfAccount[3].indexOf("=")+1, arrayOfAccount[3].length());
			account.setUserID(Integer.parseInt(userID));
			
			String solde = arrayOfAccount[4].substring(arrayOfAccount[4].indexOf("=")+1, arrayOfAccount[4].length());
			account.setSolde(Double.valueOf(solde));
			
			String accountType = arrayOfAccount[5].substring(arrayOfAccount[5].indexOf("=")+1, arrayOfAccount[5].length());
			account.setTypeOfAccount(accountType);
			
			String active = arrayOfAccount[6].substring(arrayOfAccount[6].indexOf("=")+1, arrayOfAccount[6].length()-1);
			account.setActive(Boolean.valueOf(active));
			
			
			accounts.add(account);
		}
		return accounts;
	}
	
	public static Account getOneAccountById(int id) 
	{
		ArrayList<Account> list =getAccounts(); 
		Account account = null;
		for(int i = 0 ; i <list.size() ; i++) {
			if(list.get(i).getId() == id) 
			{
				account = list.get(i);
				break;
			}
		}
		return account;
	}
	
	public static ArrayList<Account> getAccountsByUserID(int id) 
	{
		ArrayList<Account> list =getAccounts(); 
		ArrayList<Account> result= new ArrayList<Account>();
		
		for(int i = 0 ; i <list.size() ; i++) {
			if(list.get(i).getUserID() == id) 
			{
				result.add(list.get(i));
			}
		}
		return result;
	}
	
	public static int getMaxID() 
	{
		List<Account> accounts = getAccounts();
		if(accounts ==null || accounts.isEmpty()) {
			return 1;
		}
		Account account = getAccounts().stream().max(Comparator.comparing(Account::getId)).orElseThrow(NoSuchElementException::new);
		return account.getId();
	}
	
	
	public static List<Account> getAccountOfUser(int userID) {
		List<Account> acc= getAccounts().stream().filter(a->a.getUserID()==userID).collect(Collectors.toList());
		return acc;
	}
	
	
	public static String generateAccountCode() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
        int sizeOfString = 5;
        StringBuilder sb = new StringBuilder(sizeOfString);
        
        for (int i = 0; i < sizeOfString; i++) {
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
            sb.append(AlphaNumericString
                          .charAt(index));
        }
        
        String str = sb.toString();
        
        Random random = new Random();
        int number = random.nextInt(9999 -1000+1)+1000; 
        String local = LocalDate.now()+"";
        local=local.replaceAll("-", "");
		return local+str+number;
	}
	
	
	public static void updateAccount(ArrayList<Account> accounts ,Account account) throws IOException {
		//get the account we want to update 
		Account c =accounts.stream().filter(a->a.getId()==account.getId()).findAny().orElseThrow(null);
		//clean the file that contains 
		Tools.cleanDataFromFile(filePath);
		//update the file
		accounts.remove(c);
		accounts.add(account);
		
		for(int i = 0 ; i<accounts.size();i++) {
			AccountTools.writeAccountToFileAsString(accounts.get(i));
		}
		//System.out.println("done updating account");
	}
	
	public static boolean deactivateAccount( Account account) {
		
		if(!account.isActive()) return true;
		Account c = getOneAccountById(account.getId());
		
		if(c==null) return false;
		
		c.setActive(false);
		
		try {
			updateAccount(getAccounts(), c);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static boolean activateAccount(Account account)
	{
		if(account.isActive()) return true;
		
		Account c = getOneAccountById(account.getId());
		if(c == null) return false;
		
		c.setActive(true);
		
		try {
			updateAccount(getAccounts(), c);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static boolean withdrawMoney(ArrayList<Account>accounts ,Account account ,double money) {
		if(money< 0) return false;
		if(account.getSolde()<money) return false;
		if(account.getSolde()<=100.0) return false;
		if(money>10000) return false;
		if(!account.isActive()) return false;
		
		double newSolde = account.getSolde() - money;
		account.setSolde(newSolde);
		
		//update the account
		try {
			updateAccount(accounts, account);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static boolean depositMoney(ArrayList<Account> accounts , Account account,double money)
	{
		if(money< 0) return false;
		if(money>100000) return false;
		if(!account.isActive()) return false;
		
		double newSolde = money + account.getSolde();
		account.setSolde(newSolde);
		
		//update the account 
		try {
			updateAccount(accounts, account);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static void createHistoriqueForEveryAccount(Account account) {
		File historique = new File(accountHistoryFilePath+"\\"+account.getAccountCode()+".txt");
		if(!historique.exists()) {
			try {
				historique.createNewFile();
				System.out.println("created Successfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getAccountHistoryNameFile(Account account) {
		File file = new File(accountHistoryFilePath);
		File[] listOfFiles = file.listFiles();
		if(account==null) return "none";
		for(int i = 0 ; i <listOfFiles.length;i++) {
			if(listOfFiles[i].getName().equals(account.getAccountCode()+".txt")) {
				return listOfFiles[i].getName();
			}
		}
		
		return "none";
	}
	public static void writeHistoryForAccount(String history , Account account) throws Exception {
		String accountHistoryFile = getAccountHistoryNameFile(account);
		//System.out.println(userHistoryFile);
		User user = UserTools.getOneUser(account.getUserID());
		String his = "User [account code:"+account.getAccountCode() +", owner name:"+user.getFirstName()+" "+user.getLastName()+", history:"+history+" , date="+new Date()+""+"]";
		if(accountHistoryFile.isEmpty()) return;
		Tools.writeStringToFile(his, accountHistoryFilePath+"\\"+accountHistoryFile);
	}
	
	public static ArrayList<String> getHistoryOfAccount(Account account){
		String file = getAccountHistoryNameFile(account);
		if(account == null) return null;
		ArrayList<String> history = Tools.getDataFromFile(accountHistoryFilePath+"\\"+file);
		return history;
	}
	
	public static ArrayList<String> getHistoryForAllAccounts(ArrayList<Account> accounts){
		if(accounts == null) return null;
		String [] fileNames = new String[accounts.size()] ;
		for(int i = 0 ; i <accounts.size() ; i++) {
			fileNames[i] = getAccountHistoryNameFile(accounts.get(i));
		}
		ArrayList<String> history = new ArrayList<>();
		for(int j = 0 ; j<fileNames.length;j++) {
			ArrayList<String> his = new ArrayList<String>();
			his=Tools.getDataFromFile(accountHistoryFilePath+"\\"+fileNames[j]);
			for(int i = 0 ; i <his.size();i++) {
				history.add(his.get(i));
			}
		}
		return history;
	}
	
	public static void main(String[] args) throws Exception {
		/*
		 * 
		 * private int id;
	private String accountCode;
	private String dateOfCreation;
	private int userID;
	private double solde;
	private String typeOfAccount;
	private boolean active;
		 * 
		 * 
		 */
//		Account account = new Account(1,generateAccountCode(), LocalDate.now()+"", 1, 12000.0, "Single Account", true);
//		Account account1 = new Account(2,generateAccountCode(), LocalDate.now()+"", 2, 15000.0, "Single Account", true);
//		Account account2 = new Account(3,generateAccountCode(), LocalDate.now()+"", 3, 4000.0, "Single Account", true);
//		Account account3 = new Account(4,generateAccountCode(), LocalDate.now()+"", 4, 125000.0, "Saving Account", false);
//		Account account4 = new Account(5,generateAccountCode(), LocalDate.now()+"", 5, 100.0, "Common Account", false);
//		Account account5 = new Account(6,generateAccountCode(), LocalDate.now()+"", 8, 6500.0, "Single Account", true);
//		Account account6 = new Account(7,generateAccountCode(), LocalDate.now()+"", 7, 2500.0, "Single Account", false);
//		Account account7 = new Account(8,generateAccountCode(), LocalDate.now()+"", 6, 4520.0, "Single Account", true);
//		Account account8 = new Account(9,generateAccountCode(), LocalDate.now()+"", 9, 1500.0, "Single Account", false);
//		Account account9 = new Account(10,generateAccountCode(), LocalDate.now()+"", 1, 520000.0, "Saving Account", true);
//		
//		
//		writeAccountToFileAsString(account);
//		writeAccountToFileAsString(account1);
//		writeAccountToFileAsString(account2);
//		writeAccountToFileAsString(account3);
//		writeAccountToFileAsString(account4);
//		writeAccountToFileAsString(account5);
//		writeAccountToFileAsString(account6);
//		writeAccountToFileAsString(account7);
//		writeAccountToFileAsString(account8);
//		writeAccountToFileAsString(account9);
//		Account accoun = new Account(getAccounts().get(2).getId(),getAccounts().get(2).getAccountCode(), 
//				getAccounts().get(2).getDateOfCreation(), getAccounts().get(2).getUserID(), 
//				45000.0, getAccounts().get(2).getTypeOfAccount(), getAccounts().get(2).isActive());
		
		//getAccounts().forEach(System.out::println);
		//Tools.cleanDataFromFile(filePath);
		//updateAccount(getAccounts(), accoun);
//		activateAccount(getOneAccountById(11));
//		System.out.println(getOneAccountById(11));
//		
//		//System.out.println(withdrawMoney(getAccounts(), getOneAccountById(10), 8000));
//		System.out.println(depositMoney(getAccounts(), getOneAccountById(11), 5000));
//		System.out.println(getOneAccountById(11));
//		
		//getAccounts().forEach(account->createHistoriqueForEveryAccount(account));
		//getAccountOfUser(2).forEach(System.out::println);
		//writeHistoryForAccount("Has been credited with amount of 2000 DH", getOneAccountById(10));
		//getAccounts().forEach(System.out::println);
		//getAccounts().forEach(System.out::println);;
		
		ArrayList<Integer> usersID= new ArrayList<Integer>();
		
		ArrayList<User> users = UserTools.getUsers();
		for(int i = 0 ; i<users.size();i++) {
			usersID.add(users.get(i).getId());
		}
		System.out.println(usersID);
		
		List<Account> a =getAccounts().stream().filter(x->!usersID.contains(x.getUserID())).collect(Collectors.toList());
		//getHistoryOfAccount(getOneAccountById(10)).forEach(x->System.out.println(x));
		//getHistoryForAllAccounts(getAccountsByUserID(1)).forEach(System.out::println);;
		a.forEach(System.out::println);

	}
}

