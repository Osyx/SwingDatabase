import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AddArenaToTournament extends JFrame {

    private final TournamentDAO tournamentDAO;

    /**
     * Create the frame.
     */
    AddArenaToTournament(TournamentDAO newTournamentDAO, String arena, AllSportTV_GUI allSportTV_gui) {
        this.tournamentDAO = newTournamentDAO;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 143);

        JPanel panel = new JPanel();
        JLabel lblArena = new JLabel("Arena:");
        JLabel lblTournament = new JLabel("Tournament:");
        JLabel lblAddAArena = new JLabel("Add a arena to a arena");
        JPanel panel_1 = new JPanel();
        JButton btnCreate = new JButton("Create");

        JPanel contentPane = new JPanel();
        JButton btnArena = new JButton(arena);
        JComboBox<String> tourField = new JComboBox<>();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        lblArena.setHorizontalAlignment(SwingConstants.CENTER);
        lblTournament.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddAArena.setHorizontalAlignment(SwingConstants.CENTER);
        btnArena.setEnabled(false);

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(lblAddAArena, BorderLayout.NORTH);
        contentPane.add(panel_1, BorderLayout.SOUTH);

        setContentPane(contentPane);
        panel.add(lblArena);
        panel.add(lblTournament);
        panel.add(btnArena);
        panel.add(tourField);
        panel_1.add(btnCreate);
        panel.setLayout(new GridLayout(2, 2, 0, 0));

        allSportTV_gui.changeDropdown(arena, tourField, false);

        btnCreate.addActionListener(e -> {
            String tournamentLink = tourField.getSelectedItem().toString();
            tournamentDAO.linkArenaAndTournament(arena, tournamentLink);
            JOptionPane.showMessageDialog(panel, "Added!\n" + arena + " was added to " + tournamentLink);
            allSportTV_gui.refresh();
        });
    }

}
