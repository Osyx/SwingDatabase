import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

class AllSportTV_GUI extends JFrame {

    //private final JTextField arenaNameTextField;
    private final JTable table;
    private final ArenaDAO arenaDAO;
    private final TournamentDAO tournamentDAO;

    /**
     * Create the frame.
     */
    private AllSportTV_GUI() {

        this.arenaDAO = new ArenaDAO();
        this.tournamentDAO = new TournamentDAO();

        setTitle("AllSportTV");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 638, 306);

        JMenuBar menuBar = new JMenuBar();
        JMenu mnSearch = new JMenu("Search");
        JCheckBoxMenuItem chckbxmntmArenas = new JCheckBoxMenuItem("Tournaments at");
        JCheckBoxMenuItem chckbxmntmTournaments = new JCheckBoxMenuItem("Hosting arenas");
        JMenu mnCreate = new JMenu("Create");
        JMenuItem mntmCreateNewArena = new JMenuItem("Create new arena");
        JMenuItem mntmAddArenaToTournament = new JMenuItem("Add arena to tournament");
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        JLabel lblEnterText = new JLabel("Show tournaments taking place at:");
        JButton btnSearch = new JButton("Search");
        Box horizontalBox = Box.createHorizontalBox();
        JScrollPane scrollPane = new JScrollPane();
        JComboBox<String> comboBox = new JComboBox<>();

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.NORTH);
        table = new JTable();

        setContentPane(contentPane);
        setJMenuBar(menuBar);
        scrollPane.setViewportView(table);
        chckbxmntmArenas.setSelected(true);
        flowLayout.setAlignment(FlowLayout.LEFT);

        panel.add(lblEnterText);
        panel.add(comboBox);
        panel.add(btnSearch);
        panel.add(horizontalBox);
        menuBar.add(mnSearch);
        mnSearch.add(chckbxmntmArenas);
        mnSearch.add(chckbxmntmTournaments);
        menuBar.add(mnCreate);
        mnCreate.add(mntmCreateNewArena);
        mnCreate.add(mntmAddArenaToTournament);

        chckbxmntmArenas.addActionListener(e -> {
            if (!chckbxmntmArenas.getState()) {
                chckbxmntmTournaments.setState(true);
                lblEnterText.setText("Show arenas hosting:");
                changeDropdown("Tournament", comboBox);
            } else {
                chckbxmntmTournaments.setState(false);
                lblEnterText.setText("Show tournaments taking place at:");
                changeDropdown("Arena", comboBox);
            }
        });

        chckbxmntmTournaments.addActionListener(e -> {
            if (!chckbxmntmTournaments.getState()) {
                chckbxmntmArenas.setState(true);
                lblEnterText.setText("Show tournaments taking place at:");
                changeDropdown("Arena", comboBox);
            } else {
                chckbxmntmArenas.setState(false);
                lblEnterText.setText("Show arenas hosting:");
                changeDropdown("Tournament", comboBox);
            }
        });


        btnSearch.addActionListener(e -> {
            if (lblEnterText.getText().contains("arenas")) {
                List<Arena> tournamentArenas = tournamentDAO.searchTournamentArenas(comboBox.getSelectedItem().toString());
                ArenaTableModel arenaTableModel = new ArenaTableModel(tournamentArenas);
                table.setModel(arenaTableModel);

            } else {
                List<Tournament> searchArenaTournaments = arenaDAO.searchArenaTournaments(comboBox.getSelectedItem().toString());
                TournamentTableModel tournamentTableModel = new TournamentTableModel(searchArenaTournaments);
                table.setModel(tournamentTableModel);
            }
        });

        mntmCreateNewArena.addActionListener(e -> createNewCreateArena());

        mntmAddArenaToTournament.addActionListener(e -> createNewAddArenaToTournament());

        List<Arena> allArenas = arenaDAO.getAllArenas();
        String[] arenaSList = new String[allArenas.size()];
        int i = 0;
        for (Arena a : allArenas) {
            arenaSList[i] = a.getName();
            i++;
        }
        //ArenaTableModel arenaTableModel = new ArenaTableModel(allArenas);
        //table.setModel(arenaTableModel);
        comboBox.setModel(new DefaultComboBoxModel<>(arenaSList));



    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            try {
                AllSportTV_GUI frame = new AllSportTV_GUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void createNewCreateArena() {
        EventQueue.invokeLater(() -> {
            try {
                CreateArena frame = new CreateArena(arenaDAO);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void createNewAddArenaToTournament() {
        EventQueue.invokeLater(() -> {
            try {
                AddArenaToTournament frame = new AddArenaToTournament(tournamentDAO, this);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    void changeDropdown(String s, JComboBox<String> comboBox) {
        if(s.equals("Arena")) {
            List<Arena> allArenas = arenaDAO.getAllArenas();
            String[] arenaSList = new String[allArenas.size()];
            int i = 0;
            for (Arena a : allArenas) {
                arenaSList[i] = a.getName();
                i++;
            }
            comboBox.setModel(new DefaultComboBoxModel<>(arenaSList));
        } else {
            List<Tournament> allTournaments = tournamentDAO.getAllTournaments();
            String[] tournamentSList = new String[allTournaments.size()];
            int i = 0;
            for (Tournament a : allTournaments) {
                tournamentSList[i] = a.getName();
                i++;
            }
            comboBox.setModel(new DefaultComboBoxModel<>(tournamentSList));
        }
    }

}
