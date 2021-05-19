package bs.frames;

import bs.bean.Account;
import javax.swing.JOptionPane; // used for popups

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ano = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        forgotpass = new javax.swing.JLabel();
        login_btn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newaccount = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel1.setText("Account Number : ");

        ano.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel2.setText("Password : ");

        pass.setFont(new java.awt.Font("Monospaced", 1, 20)); // NOI18N

        forgotpass.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        forgotpass.setForeground(new java.awt.Color(204, 0, 0));
        forgotpass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        forgotpass.setText("Forgot Password? ");
        forgotpass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgotpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgotpassMouseClicked(evt);
            }
        });

        login_btn.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        login_btn.setText("Login");
        login_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_btnActionPerformed(evt);
            }
        });

        jMenu1.setText("New");

        newaccount.setText("Account");
        newaccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newaccountActionPerformed(evt);
            }
        });
        jMenu1.add(newaccount);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(forgotpass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ano)
                            .addComponent(pass, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(login_btn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(forgotpass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(login_btn)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void login_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_btnActionPerformed

        String accountNumber = ano.getText();
        String password = pass.getText();

        if (accountNumber.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Missing Fields");
            return;
        }

        Account account = new Account(accountNumber);  // loads account details of paticular account number
        if (account.getPassword() == null || account.getPassword().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Account Not Found");
            return;
        }

        if (!account.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(null, "Invalid password");
            return;
        }

        Dashboard dashboard = new Dashboard(accountNumber); // when account found dashboard will open
        dashboard.setVisible(true);
        dispose();

    }//GEN-LAST:event_login_btnActionPerformed

    private void forgotpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgotpassMouseClicked

        String accountNumber = JOptionPane.showInputDialog(null, "Enter Account Number");
        if (accountNumber == null || accountNumber.isEmpty()) {
            return;
        }
        Account account = new Account(accountNumber); // loads account and checks for security question
        if (account.getSecurityQuestion() == null || account.getSecurityQuestion().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Account Not Found");
            return;
        }
        String answer = JOptionPane.showInputDialog(null, "Security Question : " + account.getSecurityQuestion() + "\n\nAnswer : ");
        if (answer == null || answer.isEmpty()) { // get input from input dialog
            return;
        }

        if (account.getSecurityAnswer().equals(answer)) {//compare the answers  
            JOptionPane.showMessageDialog(null, "Your Password is : " + account.getPassword());
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Answer");
        }

    }//GEN-LAST:event_forgotpassMouseClicked

    private void newaccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newaccountActionPerformed
        AddAccount addAccount = new AddAccount();
        addAccount.setVisible(true);
    }//GEN-LAST:event_newaccountActionPerformed

    
    public static void main(String[] args) {
        new Login().setVisible(true);
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ano;
    private javax.swing.JLabel forgotpass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton login_btn;
    private javax.swing.JMenuItem newaccount;
    private javax.swing.JPasswordField pass;
    // End of variables declaration//GEN-END:variables
}
