package com.usman.csudh.bank.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exchange {
	
	private String Code;
	//private String Name;
	//private double exchangeRate;
	
	public Exchange() {
		super();
		//Code = this.Code;
		//Name = this.Name;
		//exchangeRate = this.exchangeRate;
	}

	public String getCode() {
		return Code;
	}

	//public String getName() {
	//	return Name;
	//}

	//public double getExchangeRate() {
		//return exchangeRate;
	//}

	public void setCode(String Code) {
		this.Code = Code;
	}

	//public void setName(String Name) {
		//this.Name = Name;
	//}
	
	//public void setExchangeRate(double exchangeRate) {
		//this.exchangeRate = exchangeRate;
	//}
	
	
	public String toString() {
		return  Code;
	}
     public boolean equals(Object obj) {
		
		Exchange other=(Exchange)obj;
		return this.Code.equalsIgnoreCase(other.getCode());
				
	}

	
	public HashMap<String, Double> Newexchange(String code, double ExchangeRate) throws IOException {
		 HashMap<String, Double> exchangeRates = new HashMap<>();
	
		 String Newfilepath = "C:/Users/Pedro Nunez/Downloads/exchange-rate.csv";
		 
		 try {
		  BufferedReader br = new BufferedReader(new FileReader(Newfilepath)); 
			 String line = "";
			 while((line = br.readLine()) !=null) {
		     String [] data = line.split(",");
			 exchangeRates.put(code, ExchangeRate);
			 //code.equalsIgnoreCase(data[0].toString());
				// String Name = data[1].trim();
		     // ExchangeRate = Double.parseDouble(data[2]);
			 
			System.out.println("1: "+code+" , "+ExchangeRate);
			 
			 break;
			 }
			 
		 }catch(FileNotFoundException b) {
			 System.out.println("Currency file could not be loaded, Currency Coversion Service and Foreign Currency accounts are not avaliable ");
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 
	return exchangeRates;

		 }
	}

