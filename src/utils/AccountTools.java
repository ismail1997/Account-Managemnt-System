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
	public static String filePath =Tools.DirecotryOfDocuments+"\\Applications\\applicationsData\\javaAppsData\\AccountManagementSystem\\accounts\\accounts.txt";
	private static String accountHistoryFilePath=Tools.DirecotryOfDocuments+"\\Applications\\applicationsData\\javaAppsData\\AccountManagementSystem\\historiques\\accountHistory";

	
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

}

