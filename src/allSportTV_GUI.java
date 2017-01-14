import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		currentFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		JCheckBoxMenuItem chckbxmntmArenas = new JCheckBoxMenuItem("Arenas");
		chckbxmntmArenas.setSelected(true);
		mnSearch.add(chckbxmntmArenas);
		
		JCheckBoxMenuItem chckbxmntmTournaments = new JCheckBoxMenuItem("Tournaments");
		mnSearch.add(chckbxmntmTournaments);
		
		JMenu mnCreate = new JMenu("Create");
		menuBar.add(mnCreate);
		
		JMenuItem mntmCreateNewArena = new JMenuItem("Create new arena");
		mnCreate.add(mntmCreateNewArena);
		
		JMenuItem mntmAddArenaTo = new JMenuItem("Add arena to tournament");
		mnCreate.add(mntmAddArenaTo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblEnterArena = new JLabel("Enter arena");
		panel.add(lblEnterArena);
		
		arenaNameTextField = new JTextField();
		panel.add(arenaNameTextField);
		arenaNameTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }
        });
		panel.add(btnSearch);
		
		Box horizontalBox = Box.createHorizontalBox();
		panel.add(horizontalBox);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
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
