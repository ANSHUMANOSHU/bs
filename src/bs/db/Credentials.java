
package bs.db;

public class Credentials {
    
    static int DB_MYSQL_PORT = 3306;
    static String DB_NAME = "bs";
    static String DB_HOST = "localhost";
    static String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_MYSQL_PORT + "/" + DB_NAME;
    static String DB_USERNAME = "root";
    static String DB_PASSWORD = "";
    static String DRIVER = "com.mysql.jdbc.Driver";
    
}
