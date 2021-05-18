
package bs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database extends Credentials {

    private static Connection connection = null;

    public static Connection getConnection() {

        if (connection != null) {
            return connection;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Database INIT Error : " + ex.getMessage());
            System.exit(0);
        }
        return connection;
    }
    
}
