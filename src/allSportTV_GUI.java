import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class allSportTV_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField arenaNameTextField;
	private JTable table;
	private JFrame currentFrame;
	private ArenaDAO arenaDAO;
	private TournamentDAO tournamentDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					allSportTV_GUI frame = new allSportTV_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public allSportTV_GUI() {

	    arenaDAO = new ArenaDAO();
	    tournamentDAO = new TournamentDAO();

		setTitle("AllSportTV");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 306);

        JMenuBar menuBar = new JMenuBar();
        JMenu mnSearch = new JMenu("Search");
        JCheckBoxMenuItem chckbxmntmArenas = new JCheckBoxMenuItem("Arenas");
        JCheckBoxMenuItem chckbxmntmTournaments = new JCheckBoxMenuItem("Tournaments");
        JMenu mnCreate = new JMenu("Create");
        JMenuItem mntmCreateNewArena = new JMenuItem("Create new arena");
        JMenuItem mntmAddArenaTo = new JMenuItem("Add arena to tournament");
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        JLabel lblEnterText = new JLabel("Enter arena:");
        JButton btnSearch = new JButton("Search");
        Box horizontalBox = Box.createHorizontalBox();
        JScrollPane scrollPane = new JScrollPane();

		currentFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, contentPane);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.NORTH);
        arenaNameTextField = new JTextField();
        arenaNameTextField.setColumns(10);
        table = new JTable();

        setContentPane(contentPane);
        setJMenuBar(menuBar);
        scrollPane.setViewportView(table);
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.add(lblEnterText);
        panel.add(arenaNameTextField);
        panel.add(btnSearch);
        panel.add(horizontalBox);
        menuBar.add(mnSearch);
		mnSearch.add(chckbxmntmArenas);
		mnSearch.add(chckbxmntmTournaments);
		menuBar.add(mnCreate);
		mnCreate.add(mntmCreateNewArena);
		mnCreate.add(mntmAddArenaTo);
        chckbxmntmArenas.setSelected(true);

        chckbxmntmArenas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!chckbxmntmArenas.getState()) {
                    chckbxmntmTournaments.setState(true);
                    lblEnterText.setText("Enter tournament:");
                }
                else {
                    chckbxmntmTournaments.setState(false);
                    lblEnterText.setText("Enter arena:");
                }
            }
        });

        chckbxmntmTournaments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!chckbxmntmTournaments.getState()) {
                    chckbxmntmArenas.setState(true);
                    lblEnterText.setText("Enter arena:");
                }
                else {
                    chckbxmntmArenas.setState(false);
                    lblEnterText.setText("Enter tournament:");
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Tournament> result = arenaDAO.searchArenaTournaments(arenaNameTextField.getText());
                System.out.println(result);
            }
        });

        List<Arena> allArenas = arenaDAO.getAllArenas();
        System.out.println(allArenas);
    }

	static void createNewAssociated() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShowAssociated frame2 = new ShowAssociated();
                    frame2.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
