import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Oscar on 17-01-14.
 */
public class allSportTV_GUI {

    // DB connection variable
    static protected Connection conn;
    // DB access variables
    private String URL = "jdbc:ucanaccess://TheProject.accdb";
    private String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    private String userID = "";
    private String password = "";

    // method for establishing a DB connection
    public void connect() {
        try {
            // register the driver with DriverManager
            Class.forName(driver);
            //create a connection to the database
            conn = DriverManager.getConnection(URL, userID, password);
            // Set the auto commit of the connection to false.
            // An explicit commit will be required in order to accept
            // any changes done to the DB through this connection.
            conn.setAutoCommit(false);
            //Some logging
            System.out.println("Connected to " + URL + " using "+ driver);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
