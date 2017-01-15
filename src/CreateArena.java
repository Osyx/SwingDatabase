import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.*;

public class CreateArena extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterAName;
	private JTextField txtEnterSize;
	private JTextField txtEnterLocation;
	private JTextField txtEnterBuildDate;
	private JCheckBox chckbxInUse;
	private JLabel lblCreatingANew;
    private JFrame currentFrame;
    private ArenaDAO arenaDAO;

	/**
	 * Create the frame.
	 */
	public CreateArena(ArenaDAO newArenaDAO) {
	    this.arenaDAO = newArenaDAO;

		setTitle("Create Arena");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 574, 137);

        JPanel panel = new JPanel();
        JButton btnCreate = new JButton("Create!");

		contentPane = new JPanel();
        currentFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(btnCreate, BorderLayout.SOUTH);
		contentPane.add(panel, BorderLayout.CENTER);
		lblCreatingANew = new JLabel("Creating a new Arena");
		lblCreatingANew.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterAName = new JTextField();
		txtEnterAName.setText("Enter the arenas name:");
		txtEnterAName.setToolTipText("Enter a name for the arena.");
		txtEnterAName.setColumns(10);
		txtEnterLocation = new JTextField();
		txtEnterLocation.setText("Enter the its location:");
		txtEnterLocation.setColumns(10);
		chckbxInUse = new JCheckBox("Is it in use?");
		chckbxInUse.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterSize = new JTextField();
		txtEnterSize.setText("Enter its size:");
		txtEnterSize.setColumns(10);
		txtEnterBuildDate = new JTextField();
		txtEnterBuildDate.setText("Enter its build date:");
		txtEnterBuildDate.setColumns(10);

        setContentPane(contentPane);
        panel.add(lblCreatingANew);
        panel.add(txtEnterAName);
        panel.add(txtEnterLocation);
        panel.add(chckbxInUse);
        panel.add(txtEnterSize);
        panel.add(txtEnterBuildDate);
        panel.setLayout(new GridLayout(2, 3, 0, 0));

        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String arenaName = txtEnterAName.getText();
                String location = txtEnterLocation.getText();
                String size = txtEnterSize.getText();
                String builddate = txtEnterBuildDate.getText();
                boolean active = chckbxInUse.isSelected();
                txtEnterAName.setText("Enter a name for the arena:");
                txtEnterLocation.setText("Enter its location:");
                txtEnterSize.setText("Enter its size:");
                txtEnterBuildDate.setText("Enter its build date:");
                chckbxInUse.setSelected(false);

                try {
                    arenaDAO.insertArena(arenaName, location, size, builddate, active);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(panel, "Added!\n" + arenaName + " was added to the database.");
            }
        });

        txtEnterAName.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtEnterAName.selectAll();
            }
        });

        txtEnterBuildDate.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtEnterBuildDate.selectAll();
            }
        });

        txtEnterSize.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtEnterSize.selectAll();
            }
        });

        txtEnterLocation.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtEnterLocation.selectAll();
            }
        });


	}

}
