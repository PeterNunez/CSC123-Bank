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
	private String Name;
	private double exchangeRate;
	
	public Exchange(String Code, String Name, double exchangeRate) {
		super();
		Code = this.Code;
		Name = this.Name;
		exchangeRate = this.exchangeRate;
	}

	public String getCode() {
		return Code;
	}

	public String getName() {
		return Name;
	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setCode(String code) {
		Code = code;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public static HashMap<String, Double> Newexchange(String Newfilepath) throws IOException {
		 HashMap<String, Double> exchangeRates = new HashMap<>();
		 Scanner Keyboard = new Scanner(System.in);
		 try (BufferedReader a = new BufferedReader(new FileReader(Newfilepath)) {{
			 a.readLine();
			 String line;
			 while((line = a.readLine()) !=null) {
			 String[] data = line.split(",");
			 String Code = data[0];
			 String Name = data[1];
			 Double rates = Double.parseDouble(data[2]);
			 exchangeRates.put(Code, rates);
			 }
		 }
			 
       
		 }catch(FileNotFoundException b)) {
			
			 System.out.println("Currency file could not be loaded, Currency Coversion Service and Foreign Currency accounts are not avaliable ");
		 }
		 
	return exchangeRates;

		 }
	}

