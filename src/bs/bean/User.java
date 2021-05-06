package bs.bean;

public class User {
	String accountNo,name,aadharNo,panNo,address,contact;
	byte[] image1;
	
	public User(String accountNumber) {
        this.accountNo = accountNumber;
        load(accountNumber);
    }
	
	public User() {
    }

    public User(String name, String address, String aadhar, String pan, String contact, byte[] image) {
        this.name = name;
        this.address = address;
        this.aadharNo = aadhar;
        this.panNo = pan;
        this.contact = contact;
        this.image1 = image;
    }

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
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

	public void load(String accountNo) {
		//Load
	}
	
	public void save() {
		//Save
	}
	
	public void delete(String accountNo) {
		//Delete
	}
	
	public void generateAccountNumber() {
        long now = System.currentTimeMillis();
        String delimiter = "@";
        setAccountNo(delimiter + now);
    }

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	
}
