import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class ArenaDAO {

    private static Connection conn;

    ArenaDAO() {
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

    public static Arena convertRowToArena(ResultSet resultSet) throws SQLException {

        String name = resultSet.getString("namn");
        String place = resultSet.getString("plats");
        int size = resultSet.getInt("storlek");
        Date builddate = resultSet.getDate("byggdatum");
        boolean active = resultSet.getBoolean("aktiv");

        return new Arena(name, place, size, builddate, active);
    }

    public List<Arena> getAllArenas() {
        List<Arena> list = new ArrayList<>();

        Statement myStmt;
        ResultSet myRs;

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

        PreparedStatement myStmt;
        ResultSet myRs;

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

    public void insertArena(String arenaName, String location, String size, String buildDate, boolean active) throws Exception {
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

        query = "INSERT INTO Arena (namn, plats, storlek, byggdatum, aktiv) VALUES (?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(query);

        stmt.setString(1, arenanamn);
        stmt.setString(2, plats);
        stmt.setString(3, storlek);
        stmt.setDate(4, java.sql.Date.valueOf(byggdatum));
        stmt.setBoolean(5, aktiv);

        stmt.executeUpdate();
        stmt.close();
        conn.commit();
    }
}
