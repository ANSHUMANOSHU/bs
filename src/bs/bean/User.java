package bs.bean;

import bs.db.Database;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.serial.SerialBlob;

public class User implements TableDetails{
	String accountNumber,name,aadhar,pan,address,contact;
	byte[] image;
	
	public User(String accountNumber) {
        this.accountNumber = accountNumber;
        load(accountNumber);
    }
	
	public User() {
		
    }

    public User(String name, String address, String aadhar, String pan, String contact, byte[] image) {
        this.name = name;
        this.address = address;
        this.aadhar = aadhar;
        this.pan = pan;
        this.contact = contact;
        this.image = image;
    }

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNo) {
		this.accountNumber = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadharNo) {
		this.aadhar = aadharNo;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String panNo) {
		this.pan = panNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image1) {
		this.image = image1;
	}

	public void load(String accountNo) {
		try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            String QUERY = "select * from " + TABLE_USER + " where " + U_ACCOUNT_NUMBER + " = \"" + accountNumber + "\"";
            ResultSet rs = statement.executeQuery(QUERY);
            while (rs.next()) {
                setName(rs.getString(rs.findColumn(U_NAME)));
                setAddress(rs.getString(rs.findColumn(U_ADDRESS)));
                setAadhar(rs.getString(rs.findColumn(U_AADHAR)));
                setPan(rs.getString(rs.findColumn(U_PAN)));
                setContact(rs.getString(rs.findColumn(U_CONTACT)));
                setAccountNumber(rs.getString(rs.findColumn(U_ACCOUNT_NUMBER)));
                Blob blob = rs.getBlob(rs.findColumn(U_IMAGE));
                setImage(blob.getBytes(1, (int) blob.length()));
                blob.free();
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Exception : " + ex);
        }

	}
	
	public static boolean save(User user) {
		try {

            String QUERY = " insert into " + TABLE_USER
                    + " (" + U_NAME + ","
                    + " " + U_ADDRESS + ","
                    + " " + U_AADHAR + ","
                    + " " + U_PAN + ","
                    + " " + U_CONTACT + ","
                    + " " + U_ACCOUNT_NUMBER + ","
                    + " " + U_IMAGE + ") "
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            Connection connection = Database.getConnection();
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);

            preparedStmt.setString(1, user.getName());
            preparedStmt.setString(2, user.getAddress());
            preparedStmt.setString(3, user.getAadhar());
            preparedStmt.setString(4, user.getPan());
            preparedStmt.setString(5, user.getContact());
            preparedStmt.setString(6, user.getAccountNumber());
            Blob blob = new SerialBlob(user.getImage());
            preparedStmt.setBlob(7, blob);

            return preparedStmt.execute();

        } catch (SQLException ex) {
            System.out.println("Exception : " + ex);
        }
        return false;

	}
	
	public static boolean delete(String accountNumber) {
		try {
            String QUERY = "delete from " + TABLE_USER + " where " + U_ACCOUNT_NUMBER + " = \"" + accountNumber + "\"";
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            return statement.executeUpdate(QUERY) > 0;
        } catch (SQLException ex) {
            System.out.println("Exception : " + ex);
        }
        return false;

	}
	
	public void generateAccountNumber() {
        long now = System.currentTimeMillis();
        String delimiter = "@";
        setAccountNumber(delimiter + now);
    }

	
}
