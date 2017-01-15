import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class AddArenaToTournament extends JFrame {

    private final JTextField arenaField;
	private final JTextField tourField;
    private final TournamentDAO tournamentDAO;

	/**
	 * Create the frame.
	 */
	public AddArenaToTournament(TournamentDAO newTournamentDAO) {
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
        arenaField = new JTextField();
        tourField = new JTextField();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        arenaField.setColumns(10);
        tourField.setColumns(10);

        arenaField.setHorizontalAlignment(SwingConstants.CENTER);
        tourField.setHorizontalAlignment(SwingConstants.CENTER);
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

        btnCreate.addActionListener(e -> {
            String arenaLink = arenaField.getText();
            String tournamentLink = tourField.getText();
            tournamentDAO.linkArenaAndTournament(arenaLink, tournamentLink);
            arenaField.setText("");
            tourField.setText("");
            JOptionPane.showMessageDialog(panel, "Added!\n" + arenaLink + " was added to " + tournamentLink);
        });
	}

}
