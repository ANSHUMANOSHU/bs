package bs.bean;

public interface TableDetails {
    
    //USER TABLE DETAILS
    String TABLE_USER = "user";
    String U_NAME = "name";
    String U_ADDRESS = "address";
    String U_PAN = "pan";
    String U_AADHAR = "aadhar";
    String U_CONTACT = "contact";
    String U_ACCOUNT_NUMBER = "accountnumber";
    String U_IMAGE = "image";
    
    
    //ACCOUNT TABLE DETAILS
    String TABLE_ACCOUNT = "account";
    String A_ACCOUNT_NUMBER = "accountnumber";
    String A_BALANCE = "balance";
    String A_SECURITY_QUESTION = "securityquestion";
    String A_SECURITY_ANSWER = "securityanswer";
    String A_PASSWORD = "password";
    String A_STAMP = "stamp";
    
    
    //TRANSACTIONS TABLE DETAILS
    String TABLE_TRANSACTIONS = "transactions";
    String T_ID = "id";
    String T_ACCOUNT_NUMBER = "accountnumber";
    String T_DESCRIPTION = "description";
    String T_TYPE = "type";
    String T_FINAL_BALANCE = "finalbalance";
    String T_AMOUNT = "amount";
    String T_STAMP = "stamp";
    
    
