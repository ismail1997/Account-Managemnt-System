package utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.stream.Collectors;

import entities.Account;
import entities.Reclamation;
import entities.User;

public class ReclamationTools {
	private final static String reclamationFilePath ="C:\\Users\\ismail\\Documents\\Applications\\applicationsData\\javaAppsData\\AccountManagementSystem\\settings\\reclamation\\reclamations.txt";
	
	public  static void writeReclamationToFileAsString(Reclamation reclamation) throws Exception {
		Tools.writeObjectAsStringToFile(reclamation, reclamationFilePath);
	}
	
	public static ArrayList<String> getAsStringListOfReclamation(){
		return Tools.getDataFromFile(reclamationFilePath);
	}
	
	public static ArrayList<Reclamation> getReclamations(){
		
		ArrayList<String> reclamation = getAsStringListOfReclamation();
		ArrayList<Reclamation> reclamations = new ArrayList<Reclamation>();
		for(int r = 0; r<reclamation.size();r++) {
			Reclamation rec = new Reclamation();
			
			String id = reclamation.get(r).substring(reclamation.get(r).indexOf("Reclamation(id=")+"Reclamation(id=".length(),reclamation.get(r).indexOf(", message="));
			rec.setId(Integer.valueOf(id));
			
			String message =reclamation.get(r).substring(reclamation.get(r).indexOf(", message=")+", message=".length(),reclamation.get(r).indexOf(", dateOfReclamation"));
			rec.setMessage(message);
			
			String dateOfReclamation = reclamation.get(r).substring(reclamation.get(r).indexOf("dateOfReclamation=")+"dateOfReclamation=".length(),reclamation.get(r).indexOf(", answered"));
			rec.setDateOfReclamation(dateOfReclamation);
			
			String answered = reclamation.get(r).substring(reclamation.get(r).indexOf(", answered=")+", answered=".length(),reclamation.get(r).indexOf(", userID"));
			rec.setAnswered(Boolean.valueOf(answered));
			
			String userID = reclamation.get(r).substring(reclamation.get(r).indexOf(", userID=")+", userID=".length(),reclamation.get(r).indexOf(", answer="));
			rec.setUserID(Integer.valueOf(userID));
			
			String answer = reclamation.get(r).substring(reclamation.get(r).indexOf(", answer=")+", answer=".length(),reclamation.get(r).length()-1);
			rec.setAnswer(answer);
			
			reclamations.add(rec);
		}
		
		return reclamations;
	}
	
	public static int getMaxID() 
	{
		Reclamation reclamation = getReclamations().stream().max(Comparator.comparing(Reclamation::getId)).orElseThrow(NoSuchElementException::new);
		return reclamation.getId();
	}
	
	public static List<Reclamation> getReclamationByUser(User user){
		User u = UserTools.getOneUser(user.getId());
		if(u==null) return null;
		List<Reclamation> recs =getReclamations().stream().filter(rec->rec.getUserID()==u.getId()).collect(Collectors.toList());
		return recs;
	}
	public static Reclamation getReclamationByID(int id) {
		Reclamation reclamation = getReclamations().stream().filter(x->x.getId()==id).findAny().orElseThrow(null);
		return reclamation;
	}
	
	public static void updateReclamation(ArrayList<Reclamation> reclamations ,Reclamation reclamation) throws Exception {
		//get the user we want to update 
		Reclamation c =reclamations.stream().filter(a->a.getId()==reclamation.getId()).findAny().orElseThrow(null);
		if(c==null) return;
		//clean the file that contains 
		Tools.cleanDataFromFile(reclamationFilePath);
		//update the file
		reclamations.remove(c);
		reclamations.add(reclamation);
		
		for(int i = 0 ; i<reclamations.size();i++) {
			writeReclamationToFileAsString(reclamations.get(i));
		}
		//System.out.println("done updating user");
	}
	
	public static void main(String[] args) throws Exception {
//		Reclamation reclamation = new Reclamation(1, "How could this be you are not here with me ",LocalDate.now()+"", false,7,"");
//		Reclamation reclamation1 = new Reclamation(2,"please i wanna deactivate my account",LocalDate.now()+"",false,7,"");
//		Reclamation reclamation2 = new Reclamation(3,"can not withdraw money from my account",LocalDate.now()+"",false,12,"");

//		writeReclamationToFileAsString(reclamation1);
//		writeReclamationToFileAsString(reclamation);
//		writeReclamationToFileAsString(reclamation2);
//		System.out.println(reclamation);
		//getAsStringListOfReclamation().forEach(System.out::println);
		//getReclamations().forEach(System.out::println);
		//System.out.println(getMaxID());
		//getReclamationByUser(UserTools.getOneUser(5)).forEach(System.out::println);
		//System.out.println(getReclamationByID(1));
//		Reclamation reclamation = getReclamationByID(1);
//		reclamation.setAnswer("it is because you are an idiot");
//		updateReclamation(getReclamations(), reclamation);
		//System.out.println(reclamation);
		
		
	}
	
}
