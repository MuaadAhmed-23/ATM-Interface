import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Account extends ATM{
	/*
	 * Current Account Balance
	 */
	private int balance;
	/*
	 * Account ID
	 */
	private int accid;
	/*
	 * The user this account belongs to
	 */
	private User owner;
	/*
	 * Name of the Account user
	 */
	private String name;
	/*
	 * Account transaction list
	 */
	private ArrayList<Transaction> accTransactions;
	/*
	 * List of Account ID 
	 */
	private ArrayList<Integer> userAccountID  = new ArrayList<Integer>();
	
	/*
	 * Set the name, user, account transactions list and the bank
	 */
	
	public Account(String name, User owner, ArrayList<Transaction> accTransactions, Bank theBank) {
		/*
		 * Create an empty list of transactions
		 */
		this.accTransactions = new ArrayList<Transaction>();
		
		/*
		 * Adding this Account to the user
		 */
		
		owner.addAccount(this);
		
		/*
		 * Adding this account to the bank's generalized account list
		 */
		
		theBank.addAccount(this);
		
		/*
		 * get a unique ID for the account
		 */
		
		this.accid = account_ID();
		
		/*
		 * Add this account ID to the list of Account ID
		 */
		
		this.userAccountID.add(this.accid);
		
		/*
		 * Set the name of this account
		 * User and account share name
		 */
		
		this.name = name;
	}

	/*
	 * Access the name of this account
	 */
	
	public String getName() {
		return name;
	}

	/*
	 * Access the list of account ID 
	 */
	
	public ArrayList<Integer> getUserAccountID() {
		return userAccountID;
	}
		
	/*
	 * Change current balance when perform a transaction
	 * i.e. Transfer, withdraw, deposit
	 */
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	/*
	 * Access the current balance 
	 */

	public int getBalance() {
		return balance;
	}
	
	/*
	 * create a unique random account ID for the account
	 */
	
	public int account_ID() {
		int len = 1000;
		int rand_account_Pin = 0;
		Random r = new Random();
		boolean same_ID;
		do {
			for (int i = 0; i < len; i++) {
				int generate_rand_ID = r.nextInt(len);
				rand_account_Pin += generate_rand_ID;
			}
			same_ID = false;
			for(Integer i : this.getUserAccountID()) {
				if(i == rand_account_Pin) {
					same_ID = true;
				}
			}
		} while(same_ID);
		return rand_account_Pin;
	}
}