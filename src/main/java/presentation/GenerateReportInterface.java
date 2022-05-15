package presentation;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The type Generate report interface.
 */
public class GenerateReportInterface {

	private JFrame frame;
	private JTextField startHour;
	private JTextField endHour;
	private JTextField reportTwoField;
	private JTextField r3TimesOrdered;
	private JTextField r3Price;
	private JTextField dayField;
	private JButton generateReport1;
	private JButton generateReport2;
	private JButton generateReport3;
	private JButton generateReport4;

    /**
     * Launch the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateReportInterface window = new GenerateReportInterface();
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
    public GenerateReportInterface() {
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

		startHour = new JTextField();
		startHour.setBounds(30, 30, 96, 19);
		frame.getContentPane().add(startHour);
		startHour.setColumns(10);

		endHour = new JTextField();
		endHour.setColumns(10);
		endHour.setBounds(150, 30, 96, 19);
		frame.getContentPane().add(endHour);

		JLabel lblNewLabel = new JLabel("StartHour");
		lblNewLabel.setBounds(50, 7, 45, 13);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblEndhour = new JLabel("EndHour");
		lblEndhour.setBounds(178, 7, 45, 13);
		frame.getContentPane().add(lblEndhour);

		generateReport1 = new JButton("Report 1");
		generateReport1.setBounds(318, 29, 85, 21);
		frame.getContentPane().add(generateReport1);

		reportTwoField = new JTextField();
		reportTwoField.setBounds(30, 78, 96, 19);
		frame.getContentPane().add(reportTwoField);
		reportTwoField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("noOfTimesOrdered");
		lblNewLabel_1.setBounds(30, 55, 119, 13);
		frame.getContentPane().add(lblNewLabel_1);

		generateReport2 = new JButton("Report 2");
		generateReport2.setBounds(318, 77, 85, 21);
		frame.getContentPane().add(generateReport2);

		r3TimesOrdered = new JTextField();
		r3TimesOrdered.setColumns(10);
		r3TimesOrdered.setBounds(30, 130, 96, 19);
		frame.getContentPane().add(r3TimesOrdered);

		JLabel lblNewLabel_1_1 = new JLabel("noOfTimesOrdered");
		lblNewLabel_1_1.setBounds(30, 107, 119, 13);
		frame.getContentPane().add(lblNewLabel_1_1);

		r3Price = new JTextField();
		r3Price.setColumns(10);
		r3Price.setBounds(150, 130, 96, 19);
		frame.getContentPane().add(r3Price);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(178, 107, 45, 13);
		frame.getContentPane().add(lblPrice);

		generateReport3 = new JButton("Report 3");
		generateReport3.setBounds(318, 129, 85, 21);
		frame.getContentPane().add(generateReport3);

		dayField = new JTextField();
		dayField.setColumns(10);
		dayField.setBounds(30, 180, 96, 19);
		frame.getContentPane().add(dayField);

		generateReport4 = new JButton("Report 4");
		generateReport4.setBounds(318, 179, 85, 21);
		frame.getContentPane().add(generateReport4);

		JLabel lblNewLabel_2 = new JLabel("Days");
		lblNewLabel_2.setBounds(50, 157, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		frame.setVisible(false);
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
     * Report one.
     *
     * @param listener the listener
     */
    public void reportOne(ActionListener listener){
		this.generateReport1.addActionListener(listener);
	}

    /**
     * Report two.
     *
     * @param listener the listener
     */
    public void reportTwo(ActionListener listener){
		this.generateReport2.addActionListener(listener);
	}

    /**
     * Report three.
     *
     * @param listener the listener
     */
    public void reportThree(ActionListener listener){
		this.generateReport3.addActionListener(listener);
	}

    /**
     * Report four.
     *
     * @param listener the listener
     */
    public void reportFour(ActionListener listener){
		this.generateReport4.addActionListener(listener);
	}

    /**
     * Gets start hour.
     *
     * @return the start hour
     */
    public JTextField getStartHour() {
		return startHour;
	}

    /**
     * Gets end hour.
     *
     * @return the end hour
     */
    public JTextField getEndHour() {
		return endHour;
	}

    /**
     * Gets report two field.
     *
     * @return the report two field
     */
    public JTextField getReportTwoField() {
		return reportTwoField;
	}

    /**
     * Gets r 3 times ordered.
     *
     * @return the r 3 times ordered
     */
    public JTextField getR3TimesOrdered() {
		return r3TimesOrdered;
	}

    /**
     * Gets r 3 price.
     *
     * @return the r 3 price
     */
    public JTextField getR3Price() {
		return r3Price;
	}

    /**
     * Gets day field.
     *
     * @return the day field
     */
    public JTextField getDayField() {
		return dayField;
	}

    /**
     * Gets generate report 1.
     *
     * @return the generate report 1
     */
    public JButton getGenerateReport1() {
		return generateReport1;
	}

    /**
     * Gets generate report 2.
     *
     * @return the generate report 2
     */
    public JButton getGenerateReport2() {
		return generateReport2;
	}

    /**
     * Gets generate report 3.
     *
     * @return the generate report 3
     */
    public JButton getGenerateReport3() {
		return generateReport3;
	}

    /**
     * Gets generate report 4.
     *
     * @return the generate report 4
     */
    public JButton getGenerateReport4() {
		return generateReport4;
	}
}
