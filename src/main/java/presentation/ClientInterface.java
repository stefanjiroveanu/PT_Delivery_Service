package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Client interface.
 */
public class ClientInterface {
    private JFrame frame;
    private JTable table;
    private JLabel titleLabel;
    private JLabel priceLabel;
    private JLabel ratingLabel;
    private JLabel numberOfCaloriesLabel;
    private JLabel proteinLabel;
    private JLabel sodiumLabel;
    private JLabel fatsLabel;
    private JLabel timeLabel;
    private JTextField titleField;
    private JTextField priceField;
    private JTextField ratingField;
    private JTextField numberOfCaloriesField;
    private JTextField proteinField;
    private JTextField sodiumField;
    private JTextField fatsField;
    private JTextField timeField;
    private JButton viewTableButton;
    private JButton composeButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton placeOrderButton;
    private DefaultTableModel tableModel;

    /**
     * Create the application.
     */
    public ClientInterface() {
        initialize();
    }

    /**
     * Launch the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientInterface window = new ClientInterface();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(337, 10, 539, 543);
        frame.getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("Title");
        tableModel.addColumn("Rating");
        tableModel.addColumn("Calories");
        tableModel.addColumn("Protein");
        tableModel.addColumn("Fat");
        tableModel.addColumn("Sodium");
        tableModel.addColumn("Price");
        scrollPane.setViewportView(table);

        viewTableButton = new JButton("View Table");
        viewTableButton.setBounds(40, 500, 120, 21);
        frame.getContentPane().add(viewTableButton);

        searchButton = new JButton("Search");
        searchButton.setBounds(40, 438, 120, 21);
        frame.getContentPane().add(searchButton);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(180, 438, 120, 21);
        frame.getContentPane().add(placeOrderButton);

        timeField = new JTextField();
        timeField.setBounds(180, 500, 120, 23);
        frame.getContentPane().add(timeField);
        timeField.setColumns(10);

        timeLabel = new JLabel();
        timeLabel.setText("Time");
        timeLabel.setBounds(180, 480, 182, 19);
        frame.getContentPane().add(timeLabel);

        titleLabel = new JLabel();
        titleLabel.setText("Title");
        titleLabel.setForeground(Color.gray);
        titleLabel.setBounds(40, 60, 182, 19);
        frame.getContentPane().add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(40, 60, 182, 19);
        frame.getContentPane().add(titleField);
        titleField.setColumns(10);

        priceLabel = new JLabel();
        priceLabel.setForeground(Color.gray);
        priceLabel.setText("Price");
        priceLabel.setBounds(40, 106, 182, 19);
        frame.getContentPane().add(priceLabel);


        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setBounds(40, 106, 182, 19);
        frame.getContentPane().add(priceField);

        ratingLabel = new JLabel();
        ratingLabel.setText("Rating");
        ratingLabel.setForeground(Color.gray);
        ratingLabel.setBounds(40, 152, 182, 19);
        frame.getContentPane().add(ratingLabel);


        ratingField = new JTextField();
        ratingField.setColumns(10);
        ratingField.setBounds(40, 152, 182, 19);
        frame.getContentPane().add(ratingField);

        numberOfCaloriesLabel = new JLabel();
        numberOfCaloriesLabel.setForeground(Color.gray);
        numberOfCaloriesLabel.setText("Calories");
        numberOfCaloriesLabel.setBounds(40, 199, 182, 19);
        frame.getContentPane().add(numberOfCaloriesLabel);

        numberOfCaloriesField = new JTextField();
        numberOfCaloriesField.setColumns(10);
        numberOfCaloriesField.setBounds(40, 199, 182, 19);
        frame.getContentPane().add(numberOfCaloriesField);

        proteinLabel = new JLabel();
        proteinLabel.setForeground(Color.gray);
        proteinLabel.setText("Protein");
        proteinLabel.setBounds(40, 247, 182, 19);
        frame.getContentPane().add(proteinLabel);

        proteinField = new JTextField();
        proteinField.setColumns(10);
        proteinField.setBounds(40, 247, 182, 19);
        frame.getContentPane().add(proteinField);

        sodiumLabel = new JLabel();
        sodiumLabel.setForeground(Color.gray);
        sodiumLabel.setText("Sodium");
        sodiumLabel.setBounds(40, 291, 182, 19);
        frame.getContentPane().add(sodiumLabel);

        sodiumField = new JTextField();
        sodiumField.setColumns(10);
        sodiumField.setBounds(40, 291, 182, 19);
        frame.getContentPane().add(sodiumField);

        fatsLabel = new JLabel();
        fatsLabel.setForeground(Color.gray);
        fatsLabel.setText("Fats");
        fatsLabel.setBounds(40, 332, 182, 19);
        frame.getContentPane().add(fatsLabel);


        fatsField = new JTextField();
        fatsField.setColumns(10);
        fatsField.setBounds(40, 332, 182, 19);
        frame.getContentPane().add(fatsField);
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
     * View button action listener.
     *
     * @param actionListener the action listener
     */
    public void viewButtonActionListener(ActionListener actionListener){
        viewTableButton.addActionListener(actionListener);
    }

    /**
     * Place order button action listener.
     *
     * @param actionListener the action listener
     */
    public void placeOrderButtonActionListener(ActionListener actionListener){
        placeOrderButton.addActionListener(actionListener);
    }

    /**
     * Search button action listener.
     *
     * @param actionListener the action listener
     */
    public void searchButtonActionListener(ActionListener actionListener){
        searchButton.addActionListener(actionListener);
    }

    /**
     * Gets table model.
     *
     * @return the table model
     */
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    /**
     * Gets title field.
     *
     * @return the title field
     */
    public JTextField getTitleField() {
        return titleField;
    }

    /**
     * Gets price field.
     *
     * @return the price field
     */
    public JTextField getPriceField() {
        return priceField;
    }

    /**
     * Gets rating field.
     *
     * @return the rating field
     */
    public JTextField getRatingField() {
        return ratingField;
    }

    /**
     * Gets number of calories field.
     *
     * @return the number of calories field
     */
    public JTextField getNumberOfCaloriesField() {
        return numberOfCaloriesField;
    }

    /**
     * Gets protein field.
     *
     * @return the protein field
     */
    public JTextField getProteinField() {
        return proteinField;
    }

    /**
     * Gets sodium field.
     *
     * @return the sodium field
     */
    public JTextField getSodiumField() {
        return sodiumField;
    }

    /**
     * Gets fats field.
     *
     * @return the fats field
     */
    public JTextField getFatsField() {
        return fatsField;
    }

    /**
     * Gets time field.
     *
     * @return the time field
     */
    public JTextField getTimeField() {
        return timeField;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public JTable getTable() {
        return table;
    }
}