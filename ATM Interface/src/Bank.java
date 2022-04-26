import java.time.LocalDate;
import java.util.ArrayList;    
import java.util.Random;
import java.util.Scanner;
public class Bank extends ATM{
	
	/*
	 * User List
	 */
	
	private ArrayList<User> users;
	
	/*
	 * General user Account List (Contains all account from all users)
	 */
	
	private ArrayList<Account> userAccounts;
	
	/*
	 * Account Transaction List
	 */
	
	private ArrayList<Transaction> accountTransactions;
	
	/*
	 * set the user, Account and Transaction List
	 */
	
	public Bank(ArrayList<User> users, ArrayList<Account> userAccounts, ArrayList<Transaction> accountTransactions) {
		this.users = users;
		this.userAccounts = userAccounts;
		this.accountTransactions = accountTransactions;
	}
	
	/*
	 * Access Users in User List
	 */
	
	public ArrayList<User> getUsers() {
		return users;
	}

	/*
	 * Access Accounts in User Accounts List
	 */
	
	public ArrayList<Account> getUserAccounts() {
		return userAccounts;
	}

	/*
	 * Access Account Transactions in Transaction List
	 */
	
	public ArrayList<Transaction> getAccountTransactions() {
		return accountTransactions;
	}
	
	/*
	 * Add account to the Account List of bank
	 */

	public void addAccount(Account act) {
		this.userAccounts.add(act);
	}
	
	/*
	 * Transfer to user and account of your choosing 
	 */
	
	public void transfer_to_user(User u, Account a, int amount) {
		int accBalance = a.getBalance();
		a.setBalance(accBalance += amount);
		System.out.println(u + "" + a + "" + accBalance);
	}
	
	/*
	 * Added to every transaction (Transfer, withdrawal, deposit) in the ATM class
	 * Adds transaction to the main transaction list 
	 * Adds printed transactions to the printed transaction list
	 */
	
	public void transaction_List(String transaction_Type, int transaction_amount, ArrayList<Transaction> transaction_storage, ArrayList<String> transaction_print_storage, Account owner) {
		Transaction trans = new Transaction(transaction_Type, transaction_amount, LocalDate.now(), owner);
		transaction_storage.add(trans);
		String transac = "Transaction type: " + trans.getTransaction_type() + " Amount: " + trans.getTransaction_amount() + " Date: " + trans.getTransaction_date();
		transaction_print_storage.add(transac);
	}
}