import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar on 17-01-14.
 */
@SuppressWarnings("ALL")
public class TournamentDAO {

    // DB connection variable
    static protected Connection conn;

    TournamentDAO() {

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

    public List<Tournament> getAllTournaments() {
        List<Tournament> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = conn.createStatement();
            myRs = myStmt.executeQuery("SELECT * FROM Turnering ORDER BY namn ASC");

            while(myRs.next()) {
                list.add(convertRowToTournament(myRs));
            }

            myStmt.close();
            myRs.close();

            return list;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Arena> searchTournamentArenas(String tournament) {
        List<Arena> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = conn.prepareStatement("SELECT arenanamn AS namn, plats, storlek, byggdatum, aktiv FROM Turneringsarena, Turnering, Arena WHERE Turneringsarena.arenanamn = Arena.namn AND Turneringsarena.turneringsID = Turnering.turneringsID AND Turnering.namn = ? ORDER BY arenanamn ASC");
            myStmt.setString(1, tournament);
            myRs = myStmt.executeQuery();

            while(myRs.next()) {
                list.add(ArenaDAO.convertRowToArena(myRs));
            }

            myStmt.close();
            myRs.close();

            return list;

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Tournament convertRowToTournament(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("turneringsID");
        String name = resultSet.getString("namn");
        Date startdate = resultSet.getDate("start");
        Date enddate = resultSet.getDate("slut");

        return new Tournament(id, name, startdate, enddate);
    }

    public static void main(String[] args) throws Exception {

        TournamentDAO dao = new TournamentDAO();
        System.out.println(dao.searchTournamentArenas("Kalle Anka cupen"));

        System.out.println(dao.getAllTournaments());
    }
}
