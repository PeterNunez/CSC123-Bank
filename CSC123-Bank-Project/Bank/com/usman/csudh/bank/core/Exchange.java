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
	
	public Exchange() {
		super();
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String Code) {
		this.Code = Code;
	}
	
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
			 //remember to fix later if you have time 
			 //for some reason the key value can't be found 
			 while((line = br.readLine()) !=null) {
		     String [] data = line.split(",");
		     code = data[0].trim();
		     String Name = data[1].trim();
		     ExchangeRate = Double.parseDouble(data[2]);
			 exchangeRates.put(code, ExchangeRate);
			 
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

