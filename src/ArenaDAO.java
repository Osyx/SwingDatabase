import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar on 17-01-14.
 */
public class ArenaDAO {

    // DB connection variable
    static protected Connection conn;

    ArenaDAO() {

        // DB access variables
        String URL = "jdbc:ucanaccess://TheProject.accdb";
        String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
        String userID = "";
        String password = "";

        // method for establishing a DB connection
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

    public List<Arena> getAllArenas() {
        List<Arena> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = conn.createStatement();
            myRs = myStmt.executeQuery("SELECT * FROM Arena");

            while(myRs.next()) {
                Arena tempArena = convertRowToArena(myRs);
                list.add(tempArena);
            }

            myStmt.close();

            return list;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void searchArenas() {

    }

    private Arena convertRowToArena(ResultSet resultSet) {


        return null;
    }
}
