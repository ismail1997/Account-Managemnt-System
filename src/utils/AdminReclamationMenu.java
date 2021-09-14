package utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import entities.Reclamation;
import entities.User;
import sessionManagement.SessionManagementTools;

public class AdminReclamationMenu {
	
	public static int listOfReclamationMenu() {
		Scanner scanner = new Scanner(System.in);
		int i = -1;
		ArrayList<Reclamation> reclamations = ReclamationTools.getReclamations();
		
		do {
			String leftAlignementFormat="  | %-4d | %-80s | %-20s | %-10s | %-12s                                            |%n";
			System.out.println(Menus.header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                      List of Reclamations                                                                                                                             |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  | ID   | Reclamation Message                                                              | Owner Of Reclamation | Date       | Status                                                  |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				
				for(Reclamation reclamation : reclamations) 
				{
					
						String active ="";
						
						if(reclamation.isAnswered()) {
							active ="Answered";
						}else {
							active ="Not Answered";
						}
						String fullName = UserTools.getOneUser(reclamation.getUserID()).getFirstName()+" "+UserTools.getOneUser(reclamation.getUserID()).getLastName();
						
						System.out.format(leftAlignementFormat, reclamation.getId(),reclamation.getMessage(),fullName,reclamation.getDateOfReclamation(),active);
					}

			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |       1: Answer Reclamation     | 0: Quit                                                                                                                                             |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  Your choice :"); i = scanner.nextInt();
			Menus.clrscr();
		}while(i!= 0 && i!=1 );
		
		return i;
	}
	
	public static int answerReclamation() {
		int i = -1 ;
		int choice = -1;
		Scanner scanner = new Scanner(System.in);
		String leftAlignementFormat="  | %-4d | %-20s | %-80s                                                                        |%n";
		ArrayList<Reclamation> listOfReclamations = ReclamationTools.getReclamations();
		List<Integer> idOfNoneAnsweredReclamations = new ArrayList<Integer>();
		boolean didntSelectValidReclamationID = false;
		String answer ="";
		int attempts = 0;
		while(i!=0 && i!=1) {
			System.out.println(Menus.header);
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |                                      List of Reclamations                                                                                                                             |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			if(didntSelectValidReclamationID) {
				System.out.format("  |      Plesase choose one of the reclamations to answer by selecting its ID                                                                                                             |%n");
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			}
			System.out.format("  | ID   | Owner Of Reclamation | Reclamation Message                                                                                                                                     |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			for(Reclamation  recl : listOfReclamations) {
				String fullNameOfOwner = UserTools.getOneUser(recl.getUserID()).getFirstName()+" "+UserTools.getOneUser(recl.getUserID()).getLastName();
				if(!recl.isAnswered()) 
				{
					System.out.format(leftAlignementFormat,recl.getId(),fullNameOfOwner,recl.getMessage());
					idOfNoneAnsweredReclamations.add(recl.getId());
				}
			}
			
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  |       Please choose one of the reclamations to answer by selecting its ID                                                                                                             |%n");
			System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
			System.out.format("  | Your choice :"); choice = Integer.parseInt(scanner.nextLine());
			
			if(!idOfNoneAnsweredReclamations.contains(choice)) {
				didntSelectValidReclamationID=true;
				attempts ++;
				i=-1;
			}else {
				String selectedReclamationFromat="  | You have selected this reclamation : %-100s |%n";
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format(selectedReclamationFromat,ReclamationTools.getReclamationByID(choice).getMessage());
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  | Write your answer here: "); answer = scanner.nextLine();
				System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
				System.out.format("  | Type 1 to confirm 0 to abort answering :"); i = scanner.nextInt();

				
			}
			if(attempts >=3 && didntSelectValidReclamationID) i = 0;
			if(i==1) {
				Reclamation reclam = ReclamationTools.getReclamationByID(choice);
				reclam.setAnswered(true);
				reclam.setAnswer(answer);
				
				try {
					ReclamationTools.updateReclamation(ReclamationTools.getReclamations(), reclam);
					UserTools.writeHistoryForUser("answered reclamation from "+UserTools.getOneUser(reclam.getUserID()).getFirstName()+" "+UserTools.getOneUser(reclam.getUserID()).getLastName(), SessionManagementTools.getSession().getSessionUser());
					reclamationAnsweredSuccessfully() ;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Menus.clrscr();
		}
		return i;
	}
	public static void reclamationAnsweredSuccessfully() throws InterruptedException {
		Menus.clrscr();
		System.out.println(Menus.header);
		System.out.println("  |                                                                                                                                                                                       |");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  |                                       |     Reclamation has been answered successfully and sent to reclamer  |                                                                        |%n");
		System.out.format("  |                                       +----------------------------------------------------------------------+                                                                        |%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +                                                                                                                                                                                       +%n");
		System.out.format("  +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+%n");
		TimeUnit.MILLISECONDS.sleep(2500);
		Menus.clrscr();
	}
	public static void main(String[] args) throws Exception {
		System.out.println(answerReclamation() );
	}
}
