import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class Transaction extends ATM{
	/*
	 * Account Transaction Type 
	 * i.e. Transfer, withdraw, deposit
	 */
	private String transaction_type;
	
	/*
	 * Transaction Amount 
	 */
	private int transaction_amount;
	
	/*
	 * Date Transaction occurred 
	 */
	
	private LocalDate transaction_date;
	
	/*
	 * Set the transaction type, transaction date, and transaction amount
	 */
	
	private Account owner;
	
	/*
	 * Account performing transactions
	 */
	
	public Transaction(String transaction_type, int transaction_amount, LocalDate transaction_date, Account owner) {
		this.transaction_type = transaction_type;
		this.transaction_amount = transaction_amount;
		this.transaction_date = transaction_date;
		this.owner = owner;
		transaction_date = LocalDate.now();
		DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		String formattedDate = transaction_date.format(formatObj);
	}
	
	/*
	 * Access the transaction type
	 */
	
	public String getTransaction_type() {
		return transaction_type;
	}
	
	/*
	 * Access the transaction amount
	 */

	public int getTransaction_amount() {
		return transaction_amount;
	}

	/*
	 * Access the transaction date
	 */
	
	public LocalDate getTransaction_date() {
		return transaction_date;
	}
}