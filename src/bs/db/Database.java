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
            // Registers the mentioned driver
            Class.forName(DRIVER); 

            // returns connection with the database using the registered driver
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Database INIT Error : " + ex.getMessage());
            System.exit(0);
        }
        return connection;
    }

}
