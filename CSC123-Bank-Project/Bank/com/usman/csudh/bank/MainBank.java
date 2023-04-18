package com.usman.csudh.bank;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Scanner;

import com.usman.csudh.bank.core.Account;
import com.usman.csudh.bank.core.AccountClosedException;
import com.usman.csudh.bank.core.Bank;
import com.usman.csudh.bank.core.Exchange;
import com.usman.csudh.bank.core.InsufficientBalanceException;
import com.usman.csudh.bank.core.NoSuchAccountException;
import com.usman.csudh.util.UIManager;

public class MainBank {

	//All messages are declared as constants to make it easier to change. Also, to ensure future proofing in case the application need to be made available
	//in more than one languages
	public static final String MSG_ACCOUNT_OPENED = "%nAccount opened, account number is: %s%n%n";
	public static final String MSG_ACCOUNT_CLOSED = "%nAccount number %s has been closed, balance is %s%n%n";
	public static final String MSG_ACCOUNT_NOT_FOUND = "%nAccount number %s not found! %n%n";
	
	public static final String MSG_ACCOUNT_CURRENCY ="Account Currency: ";
	public static final String MSG_CODE = "The currency you are selling:  ";
	public static final String MSG_NAME ="Name:  ";
	public static final String MSG_EXCHANGERATE = "The currency you are buying:  ";
	
	public static final String MSG_FIRST_NAME = "Enter first name:  ";
	public static final String MSG_LAST_NAME = "Enter last name:  ";
	public static final String MSG_SSN = "Enter Social Security Number:  ";
	public static final String MSG_ACCOUNT_NAME = "Enter account name:  ";
	public static final String MSG_ACCOUNT_OD_LIMIT = "Enter overdraft limit:  ";
	public static final String MSG_ACCOUNT_CREDIT_LIMIT = "Enter credit limit:  ";
	public static final String MSG_AMOUNT = "Enter amount: ";
	public static final String MSG_ACCOUNT_NUMBER = "Enter account number: ";
	public static final String MSG_ACCOUNT_ACTION = "%n%s was %s, account balance is: %s%n%n";
	

	//Declare main menu and prompt to accept user input
	public static final String[] menuOptions = { "Open Checking Account%n","Open Saving Account%n", "List Accounts%n","View Statement%n","Show Account Information%n","Deposit Funds%n", "Withdraw Funds%n",
			"Currency Conversion%n","Close an Account%n", "Exit%n" };
	public static final String MSG_PROMPT = "%nEnter choice: ";

	
	//Declare streams to accept user input / provide output
	InputStream in;
	OutputStream out;
	
	
	//Constructor
	public MainBank(InputStream in, OutputStream out) {
		this.in=in;
		this.out=out;
	}
	
	
	//Main method. 
	public static void main(String[] args) {

		new MainBank(System.in,System.out).run();

	}
	
	
	//The core of the program responsible for providing user experience.
	public void run()  {

		//Account acc;
		int option = 0;

		UIManager ui = new UIManager(this.in,this.out,menuOptions,MSG_PROMPT);
		try {

			do {
				option = ui.getMainOption(); //Render main menu
				Account acc;
				Exchange reader = new Exchange();
				//reader.Newexchange("C:/Users/Pedro Nunez/Downloads/exchange-rate.csv",null, 0.0);
				
				Scanner Keyboard = new Scanner(System.in);
				

				switch (option) {
				case 1:
					
					//Compact statement to accept user input, open account, and print the result including the account number
					ui.print(MSG_ACCOUNT_OPENED,
							new Object[] { Bank.openCheckingAccount(ui.readToken(MSG_FIRST_NAME),
									ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN),
								 ui.readToken(MSG_ACCOUNT_CURRENCY),ui.readDouble(MSG_ACCOUNT_OD_LIMIT)).getAccountNumber()});
					
					break;
				case 2:
					
					//Compact statement to accept user input, open account, and print the result including the account number
					ui.print(MSG_ACCOUNT_OPENED,
							new Object[] { Bank
									.openSavingAccount(ui.readToken(MSG_FIRST_NAME),
											ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN),ui.readToken(MSG_ACCOUNT_CURRENCY))
									.getAccountNumber() });
					break;

				case 3:
					
					//Get bank to print list of accounts to the output stream provided as method arguemnt
					Bank.listAccounts(this.out);
					
					break;
					
				case 4:
					
					//find account and get the account to print transactions to the  output stream provided in method arguments
					try {
						Bank.printAccountTransactions(ui.readInt(MSG_ACCOUNT_NUMBER),this.out);
					} catch (NoSuchAccountException e1) {
						this.handleException(ui, e1);

					}		
					
					break;

				case 5:
					try {
						int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.lookup(accountNumber);
						System.out.println("Account number: "+Bank.getaccountNumber(accountNumber));
						System.out.println("Name: "+Bank.getfirstname(accountNumber)+" "+Bank.getlastname(accountNumber));
						System.out.println("SNN: "+Bank.getSNN(accountNumber));
						System.out.println("Currency: "+Bank.getcurrency(accountNumber));
						System.out.println("Currency balance: "+Bank.getBalance(accountNumber));
						System.out.println("USD balance: "+Bank.getUSDBalance(accountNumber)+"\n");
						
					}catch(NoSuchAccountException c) {
						this.handleException(ui, c);
					}
					
					break;
				case 6:
					//find account, deposit money and print result
					
					try {
						int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.makeDeposit(accountNumber, ui.readDouble(MSG_AMOUNT));
						ui.print(MSG_ACCOUNT_ACTION, new Object[] {"Deposit","successful",Bank.getBalance(accountNumber)});
					}
					catch(NoSuchAccountException | AccountClosedException e) {
						this.handleException(ui, e);

					}
					break;
					
				case 7:
					//find account, withdraw money and print result
					try {
						int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.makeWithdrawal(accountNumber, ui.readDouble(MSG_AMOUNT));
						ui.print(MSG_ACCOUNT_ACTION, new Object[] {"Withdrawal","successful",Bank.getBalance(accountNumber)});
						
					}
					catch(NoSuchAccountException | InsufficientBalanceException e) {
						this.handleException(ui, e);

					}
					break;
					
				case 8:
					try { 
					int accountnumber=ui.readInt(MSG_ACCOUNT_NUMBER);
					Bank.lookup(accountnumber);
					System.out.println("The currency you are selling: "+Bank.getcurrency(accountnumber));
					
					System.out.print("The amount you are selling: ");
					double is = Keyboard.nextDouble();
					
					System.out.print("The currency you are buying: ");
					String word = Keyboard.next();
					
					
					System.out.println("\nThe exchange rate is "+is+" and you will get "+word.toUpperCase()+" "+Bank.getUSDBalance(accountnumber)+"\n");

					reader.Newexchange(Bank.getcurrency(accountnumber), is);
					
					}catch(NoSuchAccountException e) {
						this.handleException(ui, e);
					}
					break;

				case 9:
					//find account and close it
					
					try {
						int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.closeAccount(accountNumber);
						ui.print(MSG_ACCOUNT_CLOSED,
								new Object[] { accountNumber, Bank.getBalance(accountNumber) });
						
					} catch (NoSuchAccountException e) {
						this.handleException(ui, e);

					}
					break;
				
				}

			} while (option != menuOptions.length);

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	
	private  void handleException(UIManager ui, Exception e) throws IOException{
		ui.print(e.getMessage(), new Object[] { });
	}


}
