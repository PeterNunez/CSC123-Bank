package com.usman.csudh.bank.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exchange {
	
	//private String Code;
	//private String Name;
	//private double exchangeRate;
	
	//public Exchange(String Code, String Name, double exchangeRate) {
		//super();
		//Code = this.Code;
		//Name = this.Name;
		//this.exchangeRate = exchangeRate;
	//}

	


	



	public Map<String, Double> Newexchange(String Newfilepath) throws FileNotFoundException{
		 Map<String, Double> exchangeRates = new HashMap<>();
		 File exfile = new File(Newfilepath);
		 try {
		 Scanner Keyboard = new Scanner(exfile);
		 
		 while (Keyboard.hasNextLine()) {
			 String line = Keyboard.nextLine();
	         String[] parts = line.split(",");
	         
	         if (parts.length == 3) {
	        	  String Code = parts[0].trim();
	        	  String Name = parts[1].trim();
	        	 Double exchangeRate = Double.parseDouble(parts[2].trim());
	        	  exchangeRates.put(Code, exchangeRate);
	         }
		 }
       
		 }catch(FileNotFoundException e) {
			 System.out.println("Currency file could not be loaded, Currency Coversion Service and Foreign Currency accounts are not avaliable ");
		 }
		
	return exchangeRates;
	}
}
