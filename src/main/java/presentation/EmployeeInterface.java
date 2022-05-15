package presentation;

import bll.MenuItem;
import bll.Order;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * The type Employee interface.
 */
public class EmployeeInterface implements Observer {

	private JFrame frame;
	private JTextArea textArea;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInterface window = new EmployeeInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textArea = new JTextArea();
		textArea.setBounds(10, 10, 416, 200);
		textArea.setEditable(false)
		;
		frame.getContentPane().add(textArea);

		btnNewButton = new JButton("Prepare Order");
		btnNewButton.setBounds(134, 232, 175, 21);
		btnNewButton.addActionListener(e -> textArea.setText(""));
		frame.getContentPane().add(btnNewButton);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}

	/**
	 * Gets frame.
	 *
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Gets text area.
	 *
	 * @return the text area
	 */
	public JTextArea getTextArea() {
		return textArea;
	}


	@Override
	public void update(Observable o, Object arg) {
		List<MenuItem> items = (List<MenuItem>) arg;
		textArea.append("New Order!!!!!\n\n");
		items.forEach(item -> textArea.append(item.getName() + "\n"));
	}
}
