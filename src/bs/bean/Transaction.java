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
		try {
            
            String QUERY = " insert into " + TABLE_TRANSACTIONS
                    + " (" + T_ID + ","
                    + " " + T_ACCOUNT_NUMBER + ","
                    + " " + T_DESCRIPTION + ","
                    + " " + T_TYPE + ","
                    + " " + T_FINAL_BALANCE + ","
                    + " " + T_AMOUNT + ","
                    + " " + T_STAMP + ") "
                    + " values (UUID(), ?, ?, ?, ?, ?, ?)";
            
            Connection connection = Database.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            
            preparedStmt.setString(1, transaction.getAccountNumber());
            preparedStmt.setString(2, transaction.getDescription());
            preparedStmt.setString(3, transaction.getType());
            preparedStmt.setFloat(4, transaction.getFinalBalance());
            preparedStmt.setFloat(5, transaction.getAmount());
            preparedStmt.setLong(6, transaction.getStamp());
            
            return preparedStmt.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Exception : " + ex);
        }
		return false;
	}
	
	public static List<Transaction> get(String QUERY)
	{
		List<Transaction> transactions = new ArrayList<>();
		try {
            
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getString(resultSet.findColumn(T_ID)));
                transaction.setAccountNumber(resultSet.getString(resultSet.findColumn(T_ACCOUNT_NUMBER)));
                transaction.setDescription(resultSet.getString(resultSet.findColumn(T_DESCRIPTION)));
                transaction.setType(resultSet.getString(resultSet.findColumn(T_TYPE)));
                transaction.setFinalBalance(resultSet.getFloat(resultSet.findColumn(T_FINAL_BALANCE)));
                transaction.setAmount(resultSet.getFloat(resultSet.findColumn(T_AMOUNT)));
                transaction.setStamp(resultSet.getLong(resultSet.findColumn(T_STAMP)));
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            System.out.println("Exception : " + ex);
        }
		return transactions;
	}
	
	public static void delete(String accountNo)
	{
		try {
            String QUERY = "delete from " + TABLE_TRANSACTIONS + " where " + T_ACCOUNT_NUMBER + " = \"" + accountNumber + "\"";
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(QUERY);
        } catch (SQLException ex) {
            System.out.println("Exception : " + ex);
        }
	}
	
	public static List<Transaction> getByAccountNumber(String accountNumber) {
        String QUERY = "select * from " + TABLE_TRANSACTIONS + " where " + T_ACCOUNT_NUMBER + " = \"" + accountNumber + "\" order by " + T_STAMP + " desc";
        return get(QUERY);
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
	
	public String toString() {
        return "{" + "id=" + id + "\n accountNumber=" + accountNumber + "\n description=" + description + "\n type=" + type + "\n finalBalance=" + finalBalance + "\n amount=" + amount + "\n stamp=" + Utils.toDateTimeString(stamp) + '}';
    }
}
