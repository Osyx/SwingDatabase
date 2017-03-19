import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

class AllSportTV_GUI extends JFrame {

    //private final JTextField arenaNameTextField;
    private final JTable table;
    private final ArenaDAO arenaDAO;
    private final TournamentDAO tournamentDAO;
    private JButton btnSearch;

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
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        JLabel lblEnterText = new JLabel("Show tournaments taking place at:");
        JButton btnSearch = new JButton("Search");
        JScrollPane scrollPane = new JScrollPane();
        JComboBox<String> comboBox = new JComboBox<>();
        JButton btnAddAArena = new JButton("Add a arena to this tournament");
        Component horizontalGlue = Box.createHorizontalGlue();
        JPanel contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.NORTH);
        table = new JTable();
        this.btnSearch = btnSearch;

        setContentPane(contentPane);
        setJMenuBar(menuBar);
        scrollPane.setViewportView(table);
        chckbxmntmArenas.setSelected(true);
        flowLayout.setAlignment(FlowLayout.LEFT);
        btnAddAArena.setVisible(false);

        panel.add(lblEnterText);
        panel.add(comboBox);
        panel.add(btnSearch);
        panel.add(horizontalGlue);
        panel.add(btnAddAArena);
        menuBar.add(mnSearch);
        mnSearch.add(chckbxmntmArenas);
        mnSearch.add(chckbxmntmTournaments);
        menuBar.add(mnCreate);
        mnCreate.add(mntmCreateNewArena);

        chckbxmntmArenas.addActionListener(e -> {
            if (!chckbxmntmArenas.getState()) {
                chckbxmntmTournaments.setState(true);
                lblEnterText.setText("Show arenas hosting:");
                btnAddAArena.setVisible(true);
                changeDropdown("Tournament", comboBox, true);
            } else {
                chckbxmntmTournaments.setState(false);
                lblEnterText.setText("Show tournaments taking place at:");
                btnAddAArena.setVisible(false);
                changeDropdown("Arena", comboBox, true);
            }
        });

        chckbxmntmTournaments.addActionListener(e -> {
            if (!chckbxmntmTournaments.getState()) {
                chckbxmntmArenas.setState(true);
                lblEnterText.setText("Show tournaments taking place at:");
                btnAddAArena.setVisible(false);
                changeDropdown("Arena", comboBox, true);
            } else {
                chckbxmntmArenas.setState(false);
                lblEnterText.setText("Show arenas hosting:");
                btnAddAArena.setVisible(true);
                changeDropdown("Tournament", comboBox, true);
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

        btnAddAArena.addActionListener(e -> createNewAddArenaToTournament(comboBox.getSelectedItem().toString()));

        mntmCreateNewArena.addActionListener(e -> createNewCreateArena());

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

    private void createNewAddArenaToTournament(String tournament) {
        EventQueue.invokeLater(() -> {
            try {
                AddArenaToTournament frame = new AddArenaToTournament(tournamentDAO, tournament, this);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    void changeDropdown(String s, JComboBox<String> comboBox, boolean all) {
        if(s.equals("Tournament")) {
            List<Tournament> allTournaments = tournamentDAO.getAllTournaments();
            String[] tournamentSList = new String[allTournaments.size()];
            int i = 0;
            for (Tournament a : allTournaments) {
                tournamentSList[i] = a.getID() + " " + a.getName();
                i++;
            }
            comboBox.setModel(new DefaultComboBoxModel<>(tournamentSList));
        } else {
            List<Arena> allArenas;
            if(all)
                allArenas = arenaDAO.getAllArenas();
            else
                allArenas = arenaDAO.searchCurrentTournamentArenas(s);
            String[] arenaSList = new String[allArenas.size()];
            int i = 0;
            for (Arena a : allArenas) {
                arenaSList[i] = a.getName();
                i++;
            }
            comboBox.setModel(new DefaultComboBoxModel<>(arenaSList));
        }
    }

    void refresh() {
        btnSearch.doClick();
    }

}
