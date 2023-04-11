package com.usman.csudh.bank.core;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Bank {
	
	private static Map<Integer,Account> accounts=new TreeMap<Integer,Account>();
	
	private static Map<String, Double> exchangeRates = new HashMap<>();
	
	public static Account openCheckingAccount(String firstName, String lastName, String ssn, double overdraftLimit) {
		Customer c=new Customer(firstName,lastName, ssn);
		Account a=new CheckingAccount(c,overdraftLimit);
		accounts.put(a.getAccountNumber(), a);
		return a;
		
	}
	
	public static Account openSavingAccount(String firstName, String lastName, String ssn) {
		Customer c=new Customer(firstName,lastName, ssn);
		Account a=new SavingAccount(c);
		accounts.put(a.getAccountNumber(), a);
		return a;
		
	}

	
	
	public static Account lookup(int accountNumber) throws NoSuchAccountException{
		if(!accounts.containsKey(accountNumber)) {
			throw new NoSuchAccountException("\nAccount number: "+accountNumber+" nout found!\n\n");
		}
		
		return accounts.get(accountNumber);
	}
	
	public static void makeDeposit(int accountNumber, double amount) throws AccountClosedException, NoSuchAccountException{
		
		lookup(accountNumber).deposit(amount);
		
	}
	
	public static void makeWithdrawal(int accountNumber, double amount) throws InsufficientBalanceException, NoSuchAccountException {
		lookup(accountNumber).withdraw(amount);
	}
	
	public static void closeAccount(int accountNumber) throws NoSuchAccountException {
		lookup(accountNumber).close();
	}

	
	public static double getBalance(int accountNumber) throws NoSuchAccountException {
		return lookup(accountNumber).getBalance();
	}

	public static void listAccounts(OutputStream out) throws IOException{
		
		out.write((byte)10);
		Collection<Account> col=accounts.values();
		
		for (Account a:col) {
			out.write(a.toString().getBytes());
			out.write((byte)10);
		}
		
		out.write((byte)10);
		out.flush();
	}
	
	public static void printAccountTransactions(int accountNumber, OutputStream out) throws IOException,NoSuchAccountException{
		
		lookup(accountNumber).printTransactions(out);
	}
				
	public static Map<String, Double> exchange(String Newfilepath) {
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
