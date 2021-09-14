package utils;

import java.io.IOException;
import java.util.stream.Collectors;

import entities.User;

public class StatistiquesTools {
	
	public static int totalOfUsers() {
		return UserTools.getUsers().size();
	}
	
	public static int totalOfSimpleUsers() {
		return UserTools.getUsers().stream().filter(x->x.getUserType().equals("user")).collect(Collectors.toList()).size();
	}
	
	public static int totalOfAdminUsers() {
		return UserTools.getUsers().stream().filter(x->x.getUserType().equals("admin")).collect(Collectors.toList()).size();
	}
	
	public static int nombreOfAccounts() {
		return AccountTools.getAccounts().size();
	}
	
	public static int nombreOfActiveAccount() {
		return AccountTools.getAccounts().stream().filter(x->x.isActive()).collect(Collectors.toList()).size();
	}
	
	public static int nombreOfNoneActiveAccounts() {
		return AccountTools.getAccounts().stream().filter(x->!x.isActive()).collect(Collectors.toList()).size();
	}
	
	public static int nombreOfSingleAccount() {
		return AccountTools.getAccounts().stream().filter(x->x.getTypeOfAccount().equals("Single Account")).collect(Collectors.toList()).size();
	}
	
	public static int nombreOfSavingAccount() {
		return AccountTools.getAccounts().stream().filter(x->x.getTypeOfAccount().equals("Saving Account")).collect(Collectors.toList()).size();
	}
	
	public static int nombreOfCommonAccount() {
		return AccountTools.getAccounts().stream().filter(x->x.getTypeOfAccount().equals("Common Account")).collect(Collectors.toList()).size();
	}
	public static void main(String[] args) throws IOException {
		System.out.println(nombreOfSingleAccount());
		System.out.println(nombreOfSavingAccount());
		System.out.println(nombreOfCommonAccount());
		//AccountTools.getAccounts().forEach(System.out::println);
	}
	
	/*
	 * 
	 *System.out.format(formatOfString,"Nombre of Accounts"," accounts");
			System.out.format(formatOfString,"Nombre of Active Accounts"," accounts");
			System.out.format(formatOfString,"Nombre of None Active Accounts"," accounts");
			System.out.format(formatOfString,"Nombre of Single Accounts"," accounts");
			System.out.format(formatOfString,"Nombre of Saving Accounts"," accounts");
			System.out.format(formatOfString,"Nombre of Common Accounts"," accounts");
			System.out.format(formatOfString,"Total Sold of Accounts"," accounts");
			System.out.format(formatOfString,"Total Sold of Active Accounts"," accounts");
			System.out.format(formatOfString,"Total Sold of None Active Accounts"," accounts");
			System.out.format(formatOfString,"Total Sold of Simple Accounts"," accounts");
			System.out.format(formatOfString,"Total Sold of Common Accounts"," accounts");
	 * */
}
