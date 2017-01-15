import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar on 17-01-14.
 */
@SuppressWarnings("ALL")
public class ArenaDAO {

    // DB connection variable
    static protected Connection conn;

    ArenaDAO() {

        // DB access variables
        String URL = "jdbc:ucanaccess://src/TheProject.accdb";
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
            myRs = myStmt.executeQuery("SELECT * FROM Arena ORDER BY namn ASC");

            while(myRs.next()) {
                list.add(convertRowToArena(myRs));
            }

            myStmt.close();
            myRs.close();

            return list;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Tournament> searchArenaTournaments(String arena) {
        List<Tournament> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = conn.prepareStatement("SELECT turneringsID, namn, start, slut FROM Turnering, Turneringsarena WHERE Turnering.turneringsID = Turneringsarena.turneringsID AND Turneringsarena.arenanamn = ? ORDER BY namn ASC");
            myStmt.setString(1, arena);
            myRs = myStmt.executeQuery();

            while(myRs.next()) {
                list.add(TournamentDAO.convertRowToTournament(myRs));
            }

            myStmt.close();
            myRs.close();

            return list;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Arena convertRowToArena(ResultSet resultSet) throws SQLException {

        String name = resultSet.getString("namn");
        String place = resultSet.getString("plats");
        int size = resultSet.getInt("storlek");
        Date builddate = resultSet.getDate("byggdatum");
        boolean active = resultSet.getBoolean("aktiv");

        return new Arena(name, place, size, builddate, active);
    }

    public void insertArena(String arenaName, String location, String size, String buildDate, boolean active) throws Exception {
        // Local variables
        String query;
        PreparedStatement stmt;
        String arenanamn;
        String plats;
        String storlek;
        String byggdatum;
        boolean aktiv;

        arenanamn = arenaName;
        plats = location;
        storlek = size;
        byggdatum = buildDate;
        aktiv = active;

        // Set the SQL statement into the query variable
        query = "INSERT INTO Arena (namn, plats, storlek, byggdatum, aktiv) VALUES (?, ?, ?, ?, ?)";

        // Create a statement associated to the connection and the query.
        // The new statement is placed in the variable stmt.
        stmt = conn.prepareStatement(query);

        // Provide the values for the ?'s in the SQL statement.
        // The value of the variable arenanamn is the first,
        // plats is second, storlek is third, byggdatum is forth
        // and aktiv is fifth
        stmt.setString(1, arenanamn);
        stmt.setString(2, plats);
        stmt.setString(3, storlek);
        stmt.setDate(4, java.sql.Date.valueOf(byggdatum));
        stmt.setBoolean(5, aktiv);

        // Execute the SQL statement that is prepared in the variable stmt
        stmt.executeUpdate();

        // Close the variable stmt and release all resources bound to it
        stmt.close();
    }
}
