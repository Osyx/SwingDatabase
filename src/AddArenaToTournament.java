import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AddArenaToTournament extends JFrame {

    private final TournamentDAO tournamentDAO;

    /**
     * Create the frame.
     */
    AddArenaToTournament(TournamentDAO newTournamentDAO, AllSportTV_GUI allSportTV_gui) {
        this.tournamentDAO = newTournamentDAO;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 143);

        JPanel panel = new JPanel();
        JLabel lblArena = new JLabel("Arena:");
        JLabel lblTournament = new JLabel("Tournament:");
        JLabel lblAddAArena = new JLabel("Add a arena to a tournament");
        JPanel panel_1 = new JPanel();
        JButton btnCreate = new JButton("Create");

        JPanel contentPane = new JPanel();
        JComboBox<String> arenaField = new JComboBox<>();
        JComboBox<String> tourField = new JComboBox<>();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        lblArena.setHorizontalAlignment(SwingConstants.CENTER);
        lblTournament.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddAArena.setHorizontalAlignment(SwingConstants.CENTER);

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(lblAddAArena, BorderLayout.NORTH);
        contentPane.add(panel_1, BorderLayout.SOUTH);

        setContentPane(contentPane);
        panel.add(lblArena);
        panel.add(lblTournament);
        panel.add(arenaField);
        panel.add(tourField);
        panel_1.add(btnCreate);
        panel.setLayout(new GridLayout(2, 2, 0, 0));

        allSportTV_gui.changeDropdown("Arena", arenaField);
        allSportTV_gui.changeDropdown("Tournament", tourField);

        btnCreate.addActionListener(e -> {
            String arenaLink = arenaField.getSelectedItem().toString();
            String tournamentLink = tourField.getSelectedItem().toString();
            tournamentDAO.linkArenaAndTournament(arenaLink, tournamentLink);
            JOptionPane.showMessageDialog(panel, "Added!\n" + arenaLink + " was added to " + tournamentLink);
        });
    }

}
