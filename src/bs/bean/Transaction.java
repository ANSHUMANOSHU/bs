package bs.bean;

import java.util.ArrayList;
import java.util.List;

public class Transaction
{
	/*
	 * id - Transaction ID
	 * accountNo - Account Number
	 * description - Transaction Description
	 * type - Debit/Credit
	 * stamp - Time Stamp
	 * finalBalance - Balance after transaction
	 * amount - Transaction amount
	 * */
	private String id, accountNo, description, type;
	private float finalBalance, amount;
	private long stamp;
	
	/*
	 * Constants
	 * */
	public static final String TYPE_CREDIT = "Credit", TYPE_DEBIT = "Debit";
	
	public Transaction(String id, String accountNo, String description, String type, long stamp, float finalBalance, float amount)
	{
		this.id = id;
		this.accountNo = accountNo;
		this.description = description;
		this.type = type;
		this.stamp = stamp;
		this.finalBalance = finalBalance;
		this.amount = amount;
	}

	public static boolean save(Transaction transaction)
	{
		return false;
	}
	
	public static List<Transaction> get(String QUERY)
	{
		List<Transaction> transactions = new ArrayList<>();
		return transactions;
	}
	
	public static void delete(String accountNo)
	{
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getFinalBalance() {
		return finalBalance;
	}

	public void setFinalBalance(float finalBalance) {
		this.finalBalance = finalBalance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStamp() {
		return stamp;
	}

	public void setStamp(long stamp) {
		this.stamp = stamp;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
}
