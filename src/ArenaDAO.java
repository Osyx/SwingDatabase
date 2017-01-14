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
            myRs = myStmt.executeQuery("SELECT * FROM Arena");

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

    public List<Arena> searchArenas(String searchFor, String where) {
        List<Arena> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = conn.prepareStatement("SELECT ? FROM Arena WHERE ?");
            myStmt.setString(1, searchFor);
            myStmt.setString(2, where);
            myRs = myStmt.executeQuery();

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
            myStmt = conn.prepareStatement("SELECT namn FROM Turnering, Turneringsarena WHERE Turnering.turneringsID = Turneringsarena.turneringsID AND Turneringsarena.arenanamn = ?");
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

    public static void main(String[] args) throws Exception {

        ArenaDAO dao = new ArenaDAO();
        System.out.println(dao.searchArenaTournaments("Camp Nou"));

        System.out.println(dao.getAllArenas());
    }

}
