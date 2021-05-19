package bs.frames;

import bs.bean.Account;
import bs.viewcontroller.DashboardViewController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dashboard extends JFrame implements ActionListener {

    //CONSTANTS
    private final int ROWS = 4;
    private final int COLUMNS = 2;
    private final String[] BUTTON_NAMES = {
        "My Account",
        "Cash Deposit",
        "Cash Withdrawl",
        "Funds Transfer",
        "Account Statement",
        "Update Password",
        "Logoff",
        "Delete Account"
    };
    private final int SPACING = 7; // spacing among the components
    private final int _WIDTH = 500; // windows width
    private final int _HEIGHT = 300; // windows height
    private final String WINDOW_TITLE = "Dashboard";  // title of the window

    //OBJECTS
    private final DashboardViewController dashboardViewController;
    private final GridLayout gridLayout = new GridLayout(ROWS, COLUMNS, SPACING, SPACING);
    private final Dimension windowSize = new Dimension(_WIDTH, _HEIGHT);
    private final Font buttonFont = new Font("Monospaced", Font.BOLD, 20);
    private final MouseAdapter adapterButtons = new MouseAdapter() {

        @Override
        public void mouseEntered(MouseEvent me) {
            ((JButton) me.getComponent()).setBackground(Color.GRAY);
            ((JButton) me.getComponent()).setForeground(Color.WHITE);
            super.mouseEntered(me);
        }

        @Override
        public void mouseExited(MouseEvent me) {
            ((JButton) me.getComponent()).setBackground(Color.WHITE);
            ((JButton) me.getComponent()).setForeground(Color.BLACK);
            super.mouseExited(me);
        }

    };
    private final String accountNumber;

    public Dashboard(String accountNumber) {
        setTitle(WINDOW_TITLE);
        setResizable(false);
        setSize(windowSize); 
        setLocationRelativeTo(null); // keeps window at the center
        setLayout(gridLayout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.accountNumber = accountNumber;

        for (int i = 0; i < ROWS * COLUMNS; i++) {
            JButton button = new JButton(BUTTON_NAMES[i]);
            button.setFont(buttonFont);
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.addMouseListener(adapterButtons);
            button.addActionListener(this);
            add(button);
        }

        dashboardViewController = new DashboardViewController(new Account(accountNumber));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if (BUTTON_NAMES[0].equalsIgnoreCase(command)) {
            dashboardViewController.viewAccountDetails();
        } else if (BUTTON_NAMES[1].equalsIgnoreCase(command)) {
            dashboardViewController.depositCash();
        } else if (BUTTON_NAMES[2].equalsIgnoreCase(command)) {
            dashboardViewController.withdrawCash();
        } else if (BUTTON_NAMES[3].equalsIgnoreCase(command)) {
            dashboardViewController.transferFunds();
        } else if (BUTTON_NAMES[4].equalsIgnoreCase(command)) {
            dashboardViewController.accountStatements();
        } else if (BUTTON_NAMES[5].equalsIgnoreCase(command)) {
            dashboardViewController.updatePassword();
        } else if (BUTTON_NAMES[6].equalsIgnoreCase(command)) {
            logoff();
        } else if (BUTTON_NAMES[7].equalsIgnoreCase(command)) {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to delete account ?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                dashboardViewController.deleteAccount();
                logoff();
            }
        }
    }

    private void logoff() {
        dispose();
        Login login = new Login();
        login.setVisible(true);
    }

}
