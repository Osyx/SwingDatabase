import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AddArenaToTornament extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddArenaToTornament frame = new AddArenaToTornament();
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
	public AddArenaToTornament() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Arena:");
        JLabel lblNewLabel_1 = new JLabel("Tournament:");
        JLabel lblAddAArena = new JLabel("Add a arena to a tournament");
        JPanel panel_1 = new JPanel();
        JButton btnCreate = new JButton("Create");

        textField = new JTextField();
        textField_1 = new JTextField();
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

		panel.setLayout(new GridLayout(2, 2, 0, 0));
		panel.add(label);
		panel.add(lblNewLabel_1);
		panel.add(textField);
		panel.add(textField_1);
        panel_1.add(btnCreate);
	}

}
