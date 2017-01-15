import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ShowAssociated extends JFrame {

	private JPanel contentPane;
	private JTable table;
    private JFrame currentFrame;

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

        allSportTV_GUI.createNewAssociated();
	}

	/**
	 * Create the frame.
	 */
	public ShowAssociated() {
        setTitle("Associated tournaments:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

        JPanel panel = new JPanel();
        JButton btnClose = new JButton("Close");

        currentFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, contentPane);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(btnClose, BorderLayout.SOUTH);
		table = new JTable();

        setContentPane(contentPane);
		panel.add(table);

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                currentFrame.dispose();
			}
		});
	}

}
