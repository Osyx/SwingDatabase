import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class CreateArena extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterAName;
	private JTextField txtEnterTheArenas;
	private JTextField textField_2;
	private JTextField txtEnterIts;
	private JCheckBox chckbxIsItIn;
	private JLabel lblCreatingANew;
    private JFrame currentFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		allSportTV_GUI.createNewCreateArena();
	}

	/**
	 * Create the frame.
	 */
	public CreateArena() {
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
		textField_2 = new JTextField();
		textField_2.setText("Enter the its location:");
		textField_2.setColumns(10);
		chckbxIsItIn = new JCheckBox("Is it in use?");
		chckbxIsItIn.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterTheArenas = new JTextField();
		txtEnterTheArenas.setText("Enter its size:");
		txtEnterTheArenas.setColumns(10);
		txtEnterIts = new JTextField();
		txtEnterIts.setText("Enter its build date:");
		txtEnterIts.setColumns(10);

        setContentPane(contentPane);
        panel.add(lblCreatingANew);
        panel.add(txtEnterAName);
        panel.add(textField_2);
        panel.add(chckbxIsItIn);
        panel.add(txtEnterTheArenas);
        panel.add(txtEnterIts);
        panel.setLayout(new GridLayout(2, 3, 0, 0));
	}

}
