package bs.viewcontroller;

import bs.bean.Account;
import bs.bean.Transaction;
import bs.bean.User;
import bs.frames.TableFrame;
import bs.frames.ViewAccount;
import bs.utils.Utils;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DashboardViewController {

    private final Account account;

    public DashboardViewController(Account account) {
        this.account = account;
    }

    public void depositCash() {
        String ans = JOptionPane.showInputDialog(null, "Enter amount to deposit");
        if (!isFloat(ans)) {
            JOptionPane.showMessageDialog(null, "Invalid Amount!!!");
            return;
        }
        float amount = Float.parseFloat(ans);
        account.deposit(amount, "By Cash");

        JOptionPane.showMessageDialog(null,
                "Cash Deposit Successfully",
                "Complete",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void withdrawCash() {
        String ans = JOptionPane.showInputDialog(null, "Enter amount to Withdraw");
        if (!isFloat(ans)) {
            JOptionPane.showMessageDialog(null, "Invalid Amount!!!");
            return;
        }
        float amount = Float.parseFloat(ans);

        if (!account.withdraw(amount, "By Cash")) {
            JOptionPane.showMessageDialog(null, "Insufficient Balance...");
            return;
        }

        JOptionPane.showMessageDialog(null,
                "Cash Withdrawn Successfully",
                "Complete",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void transferFunds() {
        String accountNumber = JOptionPane.showInputDialog(null, "Enter Account Number");

        if (accountNumber == null || accountNumber.isEmpty()) {
            return;
        }

        User user = new User(accountNumber);
        Account accountTo = new Account(accountNumber);

        if (user.getName() == null || user.getName().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Account Not Found");
            return;
        }

        String message = "Account Holder Name : " + user.getName() + "\n\n Enter Amount to deposit";
        String ans = JOptionPane.showInputDialog(null, message);

        if (!isFloat(ans)) {
            JOptionPane.showMessageDialog(null, "Invalid Amount!!!");
            return;
        }

        float amount = Float.parseFloat(ans);

        if (!account.withdraw(amount, "To Account : " + accountNumber)) {
            JOptionPane.showMessageDialog(null, "Insufficient Balance...");
            return;
        }
        accountTo.deposit(amount, "From Account : " + account.getAccountNumber());
        JOptionPane.showMessageDialog(null, "Amount Transferred Successfully");
    }

    public void updatePassword() {
        String old = JOptionPane.showInputDialog(null, "Enter Old Password");
        if (old == null || old.isEmpty()) {
            return;
        }
        if (!account.getPassword().equals(old)) {
            JOptionPane.showMessageDialog(null, "Wrong Old Password");
            return;
        }
        String newPass = JOptionPane.showInputDialog(null, "Enter new Password");
        if (newPass == null || newPass.isEmpty()) {
            return;
        }
        account.setPassword(newPass);
        account.update();
        JOptionPane.showMessageDialog(null, "Password Updated Successfully...");
    }

    public void accountStatements() {
        TableFrame tableFrame = new TableFrame("Account Statements", account.getAccountNumber());
        JTable table = tableFrame.table;
        List<Transaction> transactions = Transaction.getByAccountNumber(account.getAccountNumber());
        table.setModel(toTableModel(transactions));
        tableFrame.setVisible(true);
    }

    public void deleteAccount() {
        Account.delete(account.getAccountNumber());
        User.delete(account.getAccountNumber());
        Transaction.delete(account.getAccountNumber());
        JOptionPane.showMessageDialog(null, "Account Deleted Successfully-");
    }

    public void viewAccountDetails() {
        ViewAccount viewAccount = new ViewAccount(account.getAccountNumber());
        viewAccount.setVisible(true);
    }

    private boolean isFloat(String number) {
        try {
            float temp = Float.parseFloat(number);
            return temp > 0;
        } catch (Exception ignore) {
            System.out.println("DashBoardViewController Line 133" + ignore);
        }
        return false;
    }

    private TableModel toTableModel(List<Transaction> transactions) {

        String[] header = {"ID", "Amount", "Balance(Final)", "Type", "Description", "Date-Time"};
        Object[][] data = new Object[transactions.size()][6];
        int temp = 0;
        for (Transaction transaction : transactions) {
            data[temp][0] = transaction.getId();
            data[temp][1] = transaction.getAmount();
            data[temp][2] = transaction.getFinalBalance();
            data[temp][3] = transaction.getType();
            data[temp][4] = transaction.getDescription();
            data[temp][5] = Utils.toDateTimeString(transaction.getStamp());
            temp++;
        }
        return new DefaultTableModel(data, header);
    }

}
