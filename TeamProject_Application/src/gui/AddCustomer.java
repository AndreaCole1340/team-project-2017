package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
//import java.awt.Image;
import javax.swing.JTextField;

import classes.Customer;
import dao.CustomerDBHandler;

import javax.swing.JRadioButton;

public class AddCustomer extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JButton add;
    JRadioButton rdbtnDomestic, rdbtnProfessional;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddCustomer frame = new AddCustomer();
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
    public AddCustomer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(null);

        JLabel lblEnterNewCustomer = new JLabel("Enter new customer details");
        lblEnterNewCustomer.setFont(new Font("Serif", Font.BOLD, 20));
        lblEnterNewCustomer.setForeground(Color.blue);
        lblEnterNewCustomer.setBounds(30, 11, 332, 27);
        getContentPane().add(lblEnterNewCustomer);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Serif", Font.BOLD, 15));
        lblName.setForeground(Color.blue);

        lblName.setBounds(30, 49, 61, 32);
        getContentPane().add(lblName);

        JLabel lblType = new JLabel("Type");
        lblType.setFont(new Font("Serif", Font.BOLD, 15));
        lblType.setForeground(Color.blue);
        lblType.setBounds(30, 81, 46, 17);
        getContentPane().add(lblType);

        textField = new JTextField();
        textField.setBounds(140, 49, 86, 20);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Serif", Font.BOLD, 15));
        lblAddress.setForeground(Color.blue);
        lblAddress.setBounds(30, 125, 74, 17);
        getContentPane().add(lblAddress);

        textField_1 = new JTextField();
        textField_1.setBounds(140, 125, 86, 20);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblContactNo = new JLabel("Contact No.");
        lblContactNo.setFont(new Font("Serif", Font.BOLD, 15));
        lblContactNo.setForeground(Color.blue);
        lblContactNo.setBounds(30, 178, 89, 20);
        getContentPane().add(lblContactNo);

        textField_2 = new JTextField();
        textField_2.setBounds(140, 178, 86, 20);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        rdbtnProfessional = new JRadioButton("Professional");
        rdbtnProfessional.setBounds(98, 80, 109, 23);
        getContentPane().add(rdbtnProfessional);

        rdbtnDomestic = new JRadioButton("Personal");
        rdbtnDomestic.setBounds(210, 80, 109, 23);
        getContentPane().add(rdbtnDomestic);
        contentPane = new JPanel();
        JLabel lblAddCustomer = new JLabel("\t\tEnter New Customer details");
        lblAddCustomer.setFont(new Font("Serif", Font.BOLD, 25));
        //add labels for name type address and contact
        //add fields to input name address and contact number
        //add jComboBox for type.
        ButtonGroup btg = new ButtonGroup();
        btg.add(rdbtnDomestic);
        btg.add(rdbtnProfessional);

        add = new JButton("Add Customer");
        add.setBounds(140, 228, 189, 20);
        getContentPane().add(add);
        add.addActionListener(new Listener());
    }

    public class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = "", type = "", contact = "", address = "";
            if (e.getSource() == add) {
                name = textField.getText();
                address = textField_1.getText();
                contact = textField_2.getText();
                if (rdbtnDomestic.isSelected()) {
                    type = "Personal";
                } else if (rdbtnProfessional.isSelected()) {
                    type = "Professional";
                }
            }
            CustomerDBHandler db = new CustomerDBHandler("remoteuser", "password");
            Customer c = new Customer(name, type, address, contact);
            db.write(c);
        }
    }
}