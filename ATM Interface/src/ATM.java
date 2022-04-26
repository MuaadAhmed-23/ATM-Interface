import java.util.Scanner;  
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Date;
public class ATM {
	public static void main(String[] args) {
		/*
		 * Number of attempts entered for PIN
		 */
		int count = 0;
		/*
		 * List of Users
		 */
		ArrayList<User> user = new ArrayList<User>();
		
		/*
		 * List of User Accounts
		 */
		
		ArrayList<Account> userAcc = new ArrayList<Account>();
		
		/*
		 * List of Transactions (Objects)
		 */
		
		ArrayList<Transaction> accTransactions = new ArrayList<Transaction>();
		
		/*
		 * List of Printed Transactions i.e. Transaction Type, Amount, and date
		 */
		
		ArrayList<String> printed_transaction_List = new ArrayList<String>();
		
		/*
		 * Bank Object to store user, user accounts, and account transactions 
		 */
		
		Bank newBank = new Bank(user, userAcc, accTransactions);
		
		/*
		 * Main user navigating the ATM
		 * Added to the list of users
		 */
		
		User newUser = new User("Muaad", 2134, newBank);
		user.add(newUser);
		/*
		 * Main user account performing transactions
		 */
		
		Account newAcc = new Account("Ahmed", newUser, accTransactions, newBank);
		
		/*
		 * Stored user ID and account ID to be displayed for user convenience.
		 */
		
		int userID = newUser.user_ID();
		int accountID = newAcc.account_ID();
		System.out.println(newUser.getName() + " Your user ID is: " + userID);
		System.out.println(newUser.getName() + " Your account ID is: " + accountID);
		
		/*
		 * User enters pin, user ID, and account ID to access their account.
		 * Choose from the following options:
		 * Deposit, withdraw, transfer, View Transaction List.
		 */
		
		/*
		 * Prompt Name and Pin.
		 * 3 attempts allowed for Pin
		 */
		
		do {
			System.out.println("Welcome to the Bank ! \n");
			System.out.print("Name: ");
			Scanner atm_Name_Input = new Scanner(System.in);
			String atm_Name = atm_Name_Input.nextLine();
			System.out.print("Pin: ");
			Scanner atm_Pin_Input = new Scanner(System.in);
			int atm_Pin = atm_Pin_Input.nextInt();
			if(atm_Pin == newUser.getPin()) {
				
				/*
				 * Prompt for User ID.
				 */
				
				System.out.print("User ID: ");
				Scanner atm_user_ID_Input = new Scanner(System.in);
				int atm_user_ID = atm_user_ID_Input.nextInt();
				
				if(atm_user_ID == userID) {
				/*
				 * Prompt for Account ID.
				 */
					System.out.print("Account ID: ");
					Scanner atm_acc_ID_Input = new Scanner(System.in);
					int atm_acc_ID = atm_acc_ID_Input.nextInt();
					if(atm_acc_ID == accountID) {
				/*
				 * Once access is granted create a checking and savings account.
				 */
						Account checkingAcc = new Account(atm_Name, newUser, accTransactions, newBank);
						Account savingsAcc = new Account(atm_Name, newUser, accTransactions, newBank);					
						userAcc.add(checkingAcc);
						userAcc.add(savingsAcc);
						//System.out.println(userAcc);
						
				/*
				 * Choose an option:
				 * Deposit
				 * Withdrawal
				 * Transfer i.e. Checking to savings and vice versa, User another user.
				 * View Transaction List
				 */
						char choice = '\0';
						Scanner option = new Scanner(System.in);
						System.out.println("Choose an option:\n \n 1 for Checking balance \n 2 for Saving Balance \n 3 for Withdraw \n 4 for Deposit  \n 5 for Transfer to savings \n 6 for Transfer to checking account \n 7 for Transfer to another user \n 8 for Transaction List \n 9 for Exit \n");
						int checkingAccountBalance = checkingAcc.getBalance();
						int savingsAccountBalance = savingsAcc.getBalance();
						do {
							System.out.print("Enter Choice: ");
							char choice_character = option.next().charAt(0);
							choice = Character.toUpperCase(choice_character);
							
							/*
							 * Check balance in Checking Account 
							 */
							
							if(choice == '1') {
								System.out.println("Your Checking Balance is: " + checkingAcc.getBalance());
							} 
							
							/*
							 * Check balance in Savings Account
							 */
							
							else if (choice == '2') {
								System.out.println("You Savings Balance is: " + savingsAcc.getBalance());
							} 
							
							/*
							 * Withdraw
							 */
							
							else if(choice == '3') {
								System.out.print("Withdrawal Amount ? : ");
								Scanner withdrawal_input = new Scanner(System.in);
								int withdrawal = withdrawal_input.nextInt();
								
							/*
							 * If balance is 0 then you can't withdraw
							 * Add withdrawal to the transaction list
							 */
								
								if(checkingAcc.getBalance() <= 0) {
								 	savingsAcc.setBalance(0);
									System.out.println("Please deposit money before withdrawal. Current Balance " + checkingAcc.getBalance());
								} else {
									checkingAcc.setBalance(checkingAccountBalance -= withdrawal);
									newBank.transaction_List("withdrawal", withdrawal , accTransactions, printed_transaction_List, newAcc);
								}
							} 
							
							/*
							 * Deposit
							 * Add deposit to the transaction list
							 */
							
							else if (choice == '4') {
								System.out.print("Deposit Amount ? : ");
								Scanner deposit_input = new Scanner(System.in);
								int deposit = deposit_input.nextInt();
								checkingAcc.setBalance(checkingAccountBalance += deposit);
								newBank.transaction_List("deposit",deposit , accTransactions, printed_transaction_List, newAcc);
							} 
							
							/*
							 * Transfer to Savings Account
							 * Add transfer to the transaction list
							 */
							
							else if (choice == '5') {
								System.out.print("Transfer to savings amount: ");
								Scanner transfer_input = new Scanner(System.in);
								int transfer = transfer_input.nextInt();
								checkingAcc.setBalance(checkingAccountBalance -= transfer);
								savingsAcc.setBalance(savingsAccountBalance += transfer);
								newBank.transaction_List("transfer to savings",transfer , accTransactions, printed_transaction_List, newAcc);
							} 
							
							/*
							 * Transfer to Checking Account
							 * Add transfer to the transaction list
							 */
							
							else if (choice == '6') {
								System.out.print("Transfer to checking amount: ");
								Scanner transfer_input = new Scanner(System.in);
								int transfer = transfer_input.nextInt();
								savingsAcc.setBalance(savingsAccountBalance -= transfer);
								checkingAcc.setBalance(checkingAccountBalance += transfer);
								newBank.transaction_List("transfer to checking",transfer , accTransactions, printed_transaction_List, newAcc);
							}
							
							/*
							 * Transfer to Another User:
							 * Enter the user name and account name and amount to transfer
							 */
							
							else if (choice == '7') {
								System.out.print("User name: ");
								Scanner user_input = new Scanner(System.in);
								String user_name = user_input.nextLine();
								System.out.print("account name: ");
								Scanner account_Name_Input = new Scanner(System.in);
								String account_Name = account_Name_Input.nextLine();
								System.out.print("amount: ");
								Scanner amount_Input = new Scanner(System.in);
								int amount = amount_Input.nextInt();
								
								/*
								 * Check if user and account exist
								 * Proceed with transfer.
								 * Add transfer to the transaction list
								 */
								
								for(User u: user) {
									for(Account a: userAcc) {
										if(u.getName().equals(user_name) && a.getName().equals(account_Name)) {
											
											newBank.transfer_to_user(u, a, amount);	
											checkingAcc.setBalance(checkingAccountBalance -= amount);	
											
										} else {
											
											System.out.println("No such User!");
											System.out.println("Try Again !");
											break;
											
										}
										
										break;
									}
								}
								newBank.transaction_List("transfer to user", amount, accTransactions, printed_transaction_List, newAcc);
							} 
							
							/*
							 * View Transaction List
							 */
							
							else if (choice == '8') {
								for(String i : printed_transaction_List) {
									System.out.println(i);
								}
							}
						} while (choice < '9');
						
						String appreciation = "Thanks for banking with us !!!";
						System.out.println(appreciation);
						
					} else {
						
						System.out.println("Wrong account ID! Please Try Again.");
						
					}
				} else {
					
					System.out.println("Wrong user ID! Please Try Again.");
					
				}
				
				/*
				 * If count equals 3, Lock out user out of the ATM
				 */
				
			} else {

				count++;
				System.out.println("Wrong Pin! Please Try Again.");
				
			}
			if(count == 3) {
				
				System.out.println("Your locked out of the system");
				System.out.println("Consult your bank to reset your pin");
				
			}
		} while(count != 3);
	}
}