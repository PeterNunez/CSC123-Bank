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

	public void setCode(String Code) {
		this.Code = Code;
	}

	public void setName(String Name) {
		this.Name = Name;
	}
	
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	@Override
	public String toString() {
		return  Code+","+Name+","+exchangeRate;
	}

	public HashMap<String, Double> Newexchange(String Newfilepath) throws IOException {
		 HashMap<String, Double> exchangeRates = new HashMap<>();
	
		 try {
		  BufferedReader br = new BufferedReader(new FileReader(Newfilepath)); 
			 String line = "";
			 while((line = br.readLine()) !=null) {
			 String[] data = line.split(",");
			 this.Code = data[0];
			 this.Name = data[1];
			 this.exchangeRate = Double.parseDouble(data[2]);
			 exchangeRates.put(Code, exchangeRate);
			 
			 }
			 
		 }catch(FileNotFoundException b) {
			 System.out.println("Currency file could not be loaded, Currency Coversion Service and Foreign Currency accounts are not avaliable ");
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 
	return exchangeRates;

		 }
	}

