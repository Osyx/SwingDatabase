import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournamentDAO {

    static protected Connection conn;

    TournamentDAO() {
        String URL = "jdbc:ucanaccess://src/TheProject.accdb";
        String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
        String userID = "";
        String password = "";

        try {
            Class.forName(driver);

            conn = DriverManager.getConnection(URL, userID, password);
            conn.setAutoCommit(false);

            System.out.println("Connected to " + URL + " using "+ driver);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Tournament convertRowToTournament(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("turneringsID");
        String name = resultSet.getString("namn");
        Date startdate = resultSet.getDate("start");
        Date enddate = resultSet.getDate("slut");

        return new Tournament(id, name, startdate, enddate);
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

    public void linkArenaAndTournament(String arena, String tournament) {
        List<Tournament> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = conn.prepareStatement("SELECT turneringsID FROM Turnering WHERE namn = ?");
            myStmt.setString(1, tournament);
            myRs = myStmt.executeQuery();
            myRs.next();
            Integer turneringsID = myRs.getInt("turneringsID");
            myStmt = conn.prepareStatement("INSERT INTO Turneringsarena VALUES (?, ?)");
            myStmt.setInt(1, turneringsID);
            myStmt.setString(2, arena);
            myStmt.executeUpdate();

            myStmt.close();
            myRs.close();
            conn.commit();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
