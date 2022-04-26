import java.util.ArrayList;
import java.util.Random;
public class User extends ATM{
	
	/*
	 * Name of the user
	 */
	
	private String name;
	
	/*
	 * User ID
	 */
	
	private int ID;
	
	/*
	 * User PIN
	 */
	
	private int pin;
	
	/*
	 * List of accounts belonging to user
	 */
	
	private ArrayList<Account> account;
	
	/*
	 * List of ID
	 */
	
	private ArrayList<Integer> id_Storage = new ArrayList<Integer>();
	
	/*
	 * List of PIN
	 */
	
	private ArrayList<Integer> pin_Storage = new ArrayList<Integer>();
	
	/*
	 * Set the name, PIN, and the bank the user belongs to
	 */
	
	public User(String name, int pin, Bank thebank) {
		this.name = name;
		this.pin = pin;
		this.account = new ArrayList<Account>();
		this.pin_Storage.add(pin);
		this.id_Storage.add(this.ID);
	}
	
	/*
	 * Access the name
	 */
	
	public String getName() {
		return name;
	}
	
	/*
	 * Access the pin
	 */

	public int getPin() {
		return pin;
	}
	
	/*
	 * Access the List of ID
	 */

	public ArrayList<Integer> getId_Storage() {
		return id_Storage;
	}
	
	/*
	 * Access the List of PIN
	 */
	
	public ArrayList<Integer> getPin_Storage() {
		return pin_Storage;
	}

	/*
	 * Add an account for the user
	 * Account act is the account to be added
	 */
	
	public void addAccount(Account act) {
		this.account.add(act);
	}
	
	/*
	 * Access ID 
	 */
	
	public int getID() {
		return ID;
	}
	
	/*
	 * Create a unique random user ID for the user
	 */
	
	public int user_ID () {
		int len = 100;
		int rand_user_Pin = 0;
		Random r = new Random();
		boolean same_ID;
		do {
			for (int i = 0; i < len; i++) {
				int generate_rand_ID = r.nextInt(len);
				rand_user_Pin += generate_rand_ID;
			}
			same_ID = false;
			for (Integer i: this.getId_Storage()) {
				if(i == rand_user_Pin) {
					same_ID = true;
				}
			}
		} while(same_ID);
		return rand_user_Pin;
	}
}