import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class AddArenaToTournament extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
    private JFrame currentFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		allSportTV_GUI.createNewAddArenaToTournament();
	}

	/**
	 * Create the frame.
	 */
	public AddArenaToTournament() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 143);
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Arena:");
        JLabel lblNewLabel_1 = new JLabel("Tournament:");
        JLabel lblAddAArena = new JLabel("Add a arena to a tournament");
        JPanel panel_1 = new JPanel();
        JButton btnCreate = new JButton("Create");

        contentPane = new JPanel();
        currentFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, contentPane);
        textField = new JTextField();
        textField_1 = new JTextField();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        textField.setColumns(10);
        textField_1.setColumns(10);

        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddAArena.setHorizontalAlignment(SwingConstants.CENTER);

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(lblAddAArena, BorderLayout.NORTH);
        contentPane.add(panel_1, BorderLayout.SOUTH);

        setContentPane(contentPane);
		panel.add(label);
		panel.add(lblNewLabel_1);
		panel.add(textField);
		panel.add(textField_1);
        panel_1.add(btnCreate);
        panel.setLayout(new GridLayout(2, 2, 0, 0));
	}

}
