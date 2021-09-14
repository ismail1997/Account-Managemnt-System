package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.filechooser.FileSystemView;

import entities.User;

public class Tools {
	public static final  String DirecotryOfDocuments =FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
	public static void writeObjectAsStringToFile(Object obj, String filepath) throws Exception 
	{
		String strToWrite = obj.toString();
		File file = new File(filepath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(cryptToStringObject(strToWrite));
		
		bw.newLine();
		bw.close();
		fw.close();
		//System.out.println("done writing to a file thanks in advance");
	}
	public static void writeStringToFile(String str, String filepath) throws Exception 
	{
		if(str ==null || str.equals("")) return;
		
		File file = new File(filepath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(cryptToStringObject(str));
		
		bw.newLine();
		bw.close();
		fw.close();
		//System.out.println("done writing to a file thanks in advance");
	}
	
	public static void writeObjectAsStringToFileWithoutAppend(Object obj, String filepath) throws Exception 
	{
		String strToWrite = obj.toString();
		File file = new File(filepath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),false);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(cryptToStringObject(strToWrite));
		
		bw.newLine();
		bw.close();
		fw.close();
		//System.out.println("done writing to a file thanks in advance");
	}

	public static void readObjectAsStringFromFile(String filepath) 
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			String line ;
			while ( (line=br.readLine()) != null) 
			{
				System.out.println(CryptTools.cryptAndDecryptStringObject(line));
			}
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String cryptToStringObject(String stringToCrypt) 
	{
		return CryptTools.cryptAndDecryptStringObject(stringToCrypt);
	}
	
	public static String decryptToStringObject(String stringToDecrypt)
	{
		return CryptTools.cryptAndDecryptStringObject(stringToDecrypt);
	}
	

	
	public static ArrayList<String> getDataFromFile(String filepath)
	{
		ArrayList<String> list = new ArrayList<String>();
		try {
			File file = new File(filepath);
			FileReader freader = new FileReader(file);
			BufferedReader br = new BufferedReader(freader);
			String line ;
			while ( (line=br.readLine()) != null) 
			{
				list.add(decryptToStringObject(line));
			}
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void cleanDataFromFile(String filepath) throws IOException {
		File file = new File(filepath);
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		//System.out.println("done cleaning file");
	}
	
	public static void CreateFileIfNoteExist() throws Exception {
		String applicationDirectory     = "Applications";
		  String applicationDataDirectory = "applicationsData";
		     String javaAppsData             = "javaAppsData";
		           String AccountManagementSystem  = "AccountManagementSystem";
		                String accounts                 = "accounts";
		                       String accountsFile ="accounts.txt";
		                String historiques              ="historiques";
		                       String userhistory            ="userHistory";
		                       String accounthistory         ="accountHistory";
		                String persons                  ="persons";
		                	   String personsFile = "persons.txt";
		                String settings                 ="settings";
		                	   String idstool       ="idtool";
		                	   	      String idsFile    ="ids.txt";
		                	   String reclamation   ="reclamation";
		                	   		  String reclamationsFile ="reclamations.txt";
		                	   String sharedprefercence   ="sharedPreferences";
		                	          String sessionFile     ="session.txt";
		
		//File dir = new File(applicationDirectory);
		String documentDirectory =FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
		//System.out.println(documentDirectory);
		
		/**
		 * create all directories 
		 */
		File file = new File(documentDirectory+"\\"+applicationDirectory);
		
		
		if(!file.exists()) {
			
			file.mkdir();
			file = new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory);
			//System.out.println(file);
			//System.exit(0);
			if(!file.exists()) {
				
				file.mkdir();
				file = new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData);
				
				if(!file.exists()) {
					
					file.mkdir();
					file=new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
							        AccountManagementSystem);
					if(!file.exists()) {
						
						file.mkdir(); 
						
						//create the account directory
						File file1=new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
						        AccountManagementSystem+"\\"+accounts);
						if(!file1.exists()) {
							file1.mkdir();	
							//create the account txt file 
							file1 = new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
							        AccountManagementSystem+"\\"+accounts+"\\"+accountsFile);
							if(!file1.exists()) {
								file1.createNewFile();
							}
						}
						//create the historique directory
						File file2=new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
						        AccountManagementSystem+"\\"+historiques);
						if(!file2.exists()) {
							file2.mkdir();
							
							File f1 = new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
							        AccountManagementSystem+"\\"+historiques+"\\"+userhistory);
							if(!f1.exists()) {
								f1.mkdir();
							}
							
							File f2 = new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
							        AccountManagementSystem+"\\"+historiques+"\\"+accounthistory);
							if(!f2.exists()) {
								f2.mkdir();
							}
						}
						//create the persons directory 
						File file3=new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
						        AccountManagementSystem+"\\"+persons);
						if(!file3.exists()) {
							file3.mkdir();
							file3 = new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
							        AccountManagementSystem+"\\"+persons+"\\"+personsFile);
							if(!file3.exists()) {
								file3.createNewFile();
							}
						}
						//create the settings directory 
						File file4=new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
						        AccountManagementSystem+"\\"+settings);
						if(!file4.exists()) {
							file4.mkdir();
							//create for reclamation
							File frec = new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
							        AccountManagementSystem+"\\"+settings+"\\"+reclamation);
							if(!frec.exists()) {
								frec.mkdir();
								frec=new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
								        AccountManagementSystem+"\\"+settings+"\\"+reclamation+"\\"+reclamationsFile);
								if(!frec.exists()) {
									frec.createNewFile();
								}
							}
							//create for share preference 
							File fshare = new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
							        AccountManagementSystem+"\\"+settings+"\\"+sharedprefercence);
							if(!fshare.exists()) {
								fshare.mkdir();
								fshare=new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
								        AccountManagementSystem+"\\"+settings+"\\"+sharedprefercence+"\\"+sessionFile);
								if(!fshare.exists()) {
									fshare.createNewFile();
								}
							}
							//create for id tool
							File fidtools = new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
							        AccountManagementSystem+"\\"+settings+"\\"+idstool);
							if(!fidtools.exists()) {
								fidtools.mkdir();
								fidtools=new File(documentDirectory+"\\"+applicationDirectory+"\\"+applicationDataDirectory+"\\"+javaAppsData+"\\"+
								        AccountManagementSystem+"\\"+settings+"\\"+idstool+"\\"+idsFile);
								if(!fidtools.exists()) {
									fidtools.createNewFile();
								}
							}
						}
					}
				}
			}
			
		}
	}
	

}
