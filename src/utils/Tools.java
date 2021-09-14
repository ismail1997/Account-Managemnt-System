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
	
	
	
	public static void main (String []args) throws Exception {
		
		String applicationDirectory     = "ApplicationsDE";
		  String applicationDataDirectory = "applicationsData";
		     String javaAppsData             = "javaAppsData";
		           String AccountManagementSystem  = "AccountManagementSystem";
		                String accounts                 = "accounts";
		                       String accountsFile ="accounts.txt";
		                String historiques              ="historiques";
		                       String userhistory            ="userHistory";
		                       String accounthistory         ="accountHistory";
		                String persons                  ="persons";
		                String settings                 ="settings";
		
		//File dir = new File(applicationDirectory);
		String documentDirectory =FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
		System.out.println(documentDirectory);
		
		File file = new File(documentDirectory+"\\"+applicationDirectory);
		if(file.exists()) {
			System.out.println(file.getAbsolutePath());
			System.out.println(file.isDirectory());
		}else {
			System.out.println("doesn't exist");
			//file.createNewFile();
			System.out.println(file.mkdir());
		}
		
	}
}
