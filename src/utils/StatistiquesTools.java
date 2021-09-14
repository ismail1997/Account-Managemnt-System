package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import entities.Account;
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
	
	public static double soldeOffAllAccounts() {
		return AccountTools.getAccounts().stream().mapToDouble(o->o.getSolde()).sum();
	}
	
	public static double soldeOfActiveAccounts() {
		return AccountTools.getAccounts().stream().filter(a->a.isActive()).mapToDouble(w->w.getSolde()).sum();
	}
	public static double soldeOfNoneActiveAccounts() 
	{
		return AccountTools.getAccounts().stream().filter(a->!a.isActive()).mapToDouble(w->w.getSolde()).sum();
	}
	public static double soldeOfSingleAccounts() {
		return AccountTools.getAccounts().stream().filter(a->a.getTypeOfAccount().equals("Single Account"))
				           .mapToDouble(x->x.getSolde())
				           .sum();
	}
	
	public static double soldeOfSavingAccounts() {
		return AccountTools.getAccounts().stream()
				           .filter(a->a.getTypeOfAccount().equals("Saving Account"))
				           .mapToDouble(x->x.getSolde())
				           .sum();
	}
	
	public static double soldeOfCommonAccounts() {
		return AccountTools.getAccounts().stream()
				           .filter(a->a.getTypeOfAccount().equals("Common Account"))
				           .mapToDouble(x->x.getSolde())
				           .sum();
	}
	
	public static int nombreOfReclamations() {
		return ReclamationTools.getReclamations().size();
	}
	
	public static int nombreOfAnsweredReclamations() {
		return ReclamationTools.getReclamations().stream()
				               .filter(x->x.isAnswered())
				               .collect(Collectors.toList())
				               .size();
	}
	public static int nombreOfNoneAnsweredReclamations() {
		return ReclamationTools.getReclamations().stream()
				               .filter(x->!x.isAnswered())
				               .collect(Collectors.toList())
				               .size();
	}
	public static void main(String[] args) throws IOException {
	}
	
	
}
