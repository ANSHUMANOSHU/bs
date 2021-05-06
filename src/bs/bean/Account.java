package bs.bean;

import bs.db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account implements TableDetails {

    //FIELDS
    private String accountNumber, password, securityQuestion, securityAnswer;
    private float balance;
    private long stamp;

    //CONSTRUCTORS
    public Account() {
    }

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        load(accountNumber);
    }

    public Account(String accountNumber, String password, String securityQuestion, String securityAnswer, float balance, long stamp) {
        this.accountNumber = accountNumber;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.balance = balance;
        this.stamp = stamp;
    }

    //INSTANCE METHODS
    private void load(String accountNumber) {
        try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            String QUERY = "select * from " + TABLE_ACCOUNT + " where " + A_ACCOUNT_NUMBER + " = \"" + accountNumber + "\"";
            ResultSet rs = statement.executeQuery(QUERY);
            while (rs.next()) {
                setAccountNumber(rs.getString(rs.findColumn(A_ACCOUNT_NUMBER)));
                setPassword(rs.getString(rs.findColumn(A_PASSWORD)));
                setSecurityQuestion(rs.getString(rs.findColumn(A_SECURITY_QUESTION)));
                setSecurityAnswer(rs.getString(rs.findColumn(A_SECURITY_ANSWER)));
                setBalance(rs.getFloat(rs.findColumn(A_BALANCE)));
                setStamp(rs.getLong(rs.findColumn(A_STAMP)));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Exception : " + ex);
        }
    }

    public boolean update() {
        try {
            Connection connection = Database.getConnection();

            String QUERY = "update " + TABLE_ACCOUNT + " set "
                    + A_PASSWORD + " = ?,"
                    + A_BALANCE + " = ?"
                    + " where " + A_ACCOUNT_NUMBER + " = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, getPassword());
            preparedStatement.setFloat(2, getBalance());
            preparedStatement.setString(3, getAccountNumber());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Exception : " + ex);
        }
        return false;
    }

    //STATIC METHODS
    public static boolean save(Account account) {
        try {

            String QUERY = " insert into " + TABLE_ACCOUNT
                    + " (" + A_ACCOUNT_NUMBER + ","
                    + " " + A_PASSWORD + ","
                    + " " + A_SECURITY_QUESTION + ","
                    + " " + A_SECURITY_ANSWER + ","
                    + " " + A_BALANCE + ","
                    + " " + A_STAMP + ") "
                    + " values (?, ?, ?, ?, ?, ?)";

            Connection connection = Database.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);

            preparedStmt.setString(1, account.getAccountNumber());
            preparedStmt.setString(2, account.getPassword());
            preparedStmt.setString(3, account.getSecurityQuestion());
            preparedStmt.setString(4, account.getSecurityAnswer());
            preparedStmt.setFloat(5, account.getBalance());
            preparedStmt.setLong(6, account.getStamp());

            return preparedStmt.execute();

        } catch (SQLException ex) {
            System.out.println("Exception : " + ex);
        }
        return false;
    }
    
    public static boolean delete(String accoutNumber) {
        try {
            String QUERY = "delete from " + TABLE_ACCOUNT  + " where " + A_ACCOUNT_NUMBER + " = \"" + accoutNumber + "\"";
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            return statement.executeUpdate(QUERY) > 0;
        } catch (SQLException ex) {
            System.out.println("Exception : " + ex);
        }
        return false;
    }
    
    public void deposit(float amount, String description) {
        Account account = new Account(accountNumber);
        account.setBalance(account.getBalance() + amount);
        account.update();

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setFinalBalance(account.getBalance());
        transaction.setDescription(description);
        transaction.setType(Transaction.TYPE_CREDIT);
        transaction.setStamp(System.currentTimeMillis());
        Transaction.save(transaction);

    }

    public boolean withdraw(float amount, String description) {
        Account account = new Account(accountNumber);

        if (amount > account.getBalance()) {
            return false;
        }

        account.setBalance(account.getBalance() - amount);
        account.update();

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setFinalBalance(account.getBalance());
        transaction.setDescription(description);
        transaction.setType(Transaction.TYPE_DEBIT);
        transaction.setStamp(System.currentTimeMillis());
        Transaction.save(transaction);
        return true;
    }
    
    //GETTERS SETTERS
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public long getStamp() {
        return stamp;
    }

    public void setStamp(long stamp) {
        this.stamp = stamp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
