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

	    this.arenaDAO = new ArenaDAO();
	    this.tournamentDAO = new TournamentDAO();

		setTitle("AllSportTV");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 306);

        JMenuBar menuBar = new JMenuBar();
        JMenu mnSearch = new JMenu("Search");
        JCheckBoxMenuItem chckbxmntmArenas = new JCheckBoxMenuItem("Arenas");
        JCheckBoxMenuItem chckbxmntmTournaments = new JCheckBoxMenuItem("Tournaments");
        JMenu mnCreate = new JMenu("Create");
        JMenuItem mntmCreateNewArena = new JMenuItem("Create new arena");
        JMenuItem mntmAddArenaToTournament = new JMenuItem("Add arena to tournament");
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        JLabel lblEnterText = new JLabel("Enter arena:");
        JButton btnSearch = new JButton("Search");
        Box horizontalBox = Box.createHorizontalBox();
        JScrollPane scrollPane = new JScrollPane();

        contentPane = new JPanel();
        currentFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, contentPane);
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
        chckbxmntmArenas.setSelected(true);
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
		mnCreate.add(mntmAddArenaToTournament);

        chckbxmntmArenas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!chckbxmntmArenas.getState()) {
                    chckbxmntmTournaments.setState(true);
                    lblEnterText.setText("Enter tournament:");
                    btnSearch.doClick();
                }
                else {
                    chckbxmntmTournaments.setState(false);
                    lblEnterText.setText("Enter arena:");
                    btnSearch.doClick();
                }
            }
        });

        chckbxmntmTournaments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!chckbxmntmTournaments.getState()) {
                    chckbxmntmArenas.setState(true);
                    lblEnterText.setText("Enter arena:");
                    btnSearch.doClick();
                }
                else {
                    chckbxmntmArenas.setState(false);
                    lblEnterText.setText("Enter tournament:");
                    btnSearch.doClick();
                }
            }
        });


        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(lblEnterText.getText().contains("tournament")) {
                    if (arenaNameTextField.getText().isEmpty() || arenaNameTextField.getText().trim().length() < 1) {
                        List<Tournament> allTournaments = tournamentDAO.getAllTournaments();
                        TournamentTableModel tournamentTableModel = new TournamentTableModel(allTournaments);
                        table.setModel(tournamentTableModel);
                    } else {
                        List<Arena> tournamentArenas = tournamentDAO.searchTournamentArenas(arenaNameTextField.getText());

                        ArenaTableModel arenaTableModel = new ArenaTableModel(tournamentArenas);
                        table.setModel(arenaTableModel);
                    }
                } else {
                    if (arenaNameTextField.getText().isEmpty() || arenaNameTextField.getText().trim().length() < 1) {
                        List<Arena> allArenas = arenaDAO.getAllArenas();
                        ArenaTableModel arenaTableModel = new ArenaTableModel(allArenas);
                        table.setModel(arenaTableModel);
                    } else {
                        List<Tournament> searchArenaTournaments = arenaDAO.searchArenaTournaments(arenaNameTextField.getText());

                        TournamentTableModel tournamentTableModel = new TournamentTableModel(searchArenaTournaments);
                        table.setModel(tournamentTableModel);
                    }
                }
            }
        });

        mntmCreateNewArena.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createNewCreateArena();
            }
        });

        mntmAddArenaToTournament.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createNewAddArenaToTournament();
            }
        });

        List<Arena> allArenas = arenaDAO.getAllArenas();
        ArenaTableModel arenaTableModel = new ArenaTableModel(allArenas);
        table.setModel(arenaTableModel);

    }

    void createNewCreateArena() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateArena frame = new CreateArena(arenaDAO);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void createNewAddArenaToTournament() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddArenaToTournament frame = new AddArenaToTournament(tournamentDAO);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
