package com.usman.csudh.bank.core;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

//import com.usman.csudh.bank.Objects;

public class Bank {
	
	private static Map<Integer,Account> accounts=new TreeMap<Integer,Account>();
	//private static Map<String, Double> exchangeRates = new HashMap<String, Double>();
	
	
	
	//public static Exchange exchange(String Code) throws FileNotFoundException {
	//	Exchange e = new Exchange(Code);
		//exchangeRates.put(Code);
	//	return e;
	//}
	
	public static Account openCheckingAccount(String firstName, String lastName, String ssn, String currency, double overdraftLimit ) {
		Customer c=new Customer(firstName,lastName, ssn,currency);
		Account a=new CheckingAccount(c,overdraftLimit);
		accounts.put(a.getAccountNumber(), a);
		return a;
		
	}
	
	public static Account openSavingAccount(String firstName, String lastName, String ssn, String currency) {
		Customer c=new Customer(firstName,lastName, ssn, currency);
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
	public static double getUSDBalance(int accountNumber)throws NoSuchAccountException {
		return lookup(accountNumber).getUSDBalance();
	}

	public static String getcurrency(int accountnumber) throws NoSuchAccountException {
		return lookup(accountnumber).getAccountHolder().getCurrency();
	}
	public static int getaccountNumber(int accountnumber)throws NoSuchAccountException {
		return lookup(accountnumber).getAccountNumber();
	}
	public static String getfirstname(int accountnumber)throws NoSuchAccountException {
		return lookup(accountnumber).getAccountHolder().getFirstName();
	}
	public static String getlastname(int accountnumber)throws NoSuchAccountException {
		return lookup(accountnumber).getAccountHolder().getLastName();
	}
	public static String getSNN(int accountnumber)throws NoSuchAccountException {
		return lookup(accountnumber).getAccountHolder().getSSN();
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
	
}
