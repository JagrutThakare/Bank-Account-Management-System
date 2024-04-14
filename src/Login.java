import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3;

    Login() {
        setTitle("ANY TIME MONEY");

        // Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 10, 100, 100);
        add(l11);

        // Title
        l1 = new JLabel("WELCOME TO ATM");
        l1.setBounds(200, 40, 450, 40);
        l1.setFont(new Font("Poppins", Font.BOLD, 24)); // Increased font size
        add(l1);

        // Card Number Label
        l2 = new JLabel("Card No:");
        l2.setBounds(125, 150, 375, 30);
        l2.setFont(new Font("Poppins", Font.BOLD, 16)); // Increased font size
        add(l2);

        // Card Number TextField
        tf1 = new JTextField(15);
        tf1.setBounds(300, 150, 230, 30);
        tf1.setFont(new Font("Poppins", Font.PLAIN, 14)); // Setting font size
        add(tf1);

        // PIN Label
        l3 = new JLabel("PIN:");
        l3.setBounds(125, 220, 375, 30);
        l3.setFont(new Font("Poppins", Font.BOLD, 16)); // Increased font size
        add(l3);

        // PIN PasswordField
        pf2 = new JPasswordField(15);
        pf2.setBounds(300, 220, 230, 30);
        pf2.setFont(new Font("Poppins", Font.PLAIN, 14)); // Setting font size
        add(pf2);

        // Buttons
        Font buttonFont = new Font("Poppins", Font.BOLD, 14);

        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(buttonFont);

        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(buttonFont);

        b3 = new JButton("SIGN UP");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setFont(buttonFont);

        setLayout(null);

        // Button placements
        b1.setBounds(300, 300, 100, 30);
        add(b1);

        b2.setBounds(430, 300, 100, 30);
        add(b2);

        b3.setBounds(300, 350, 230, 30);
        add(b3);

        // Button action listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);

        // JFrame configuration methods moved to the end
        configureFrame();
    }

    // JFrame configuration methods
    private void configureFrame() {
        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Set the JFrame size
        int frameWidth = 800;
        int frameHeight = 480;
        setSize(frameWidth, frameHeight);

        // Calculate the center position
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;

        // Set the JFrame position
        setLocation(x, y);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                // Database connection
                Conn c1 = new Conn();
                String cardno = tf1.getText();
                String pin = new String(pf2.getPassword());
                System.out.println(cardno + " " + pin);
                String q = "select * from login where cardnumber = '" + cardno + "' and pin = '" + pin + "'";

                ResultSet rs = c1.s.executeQuery(q);
                if (rs.next()) {
                    dispose();
                    new Transactions(pin).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if (ae.getSource() == b2) {
                // Clearing fields
                tf1.setText("");
                pf2.setText("");
            } else if (ae.getSource() == b3) {
                // Opening sign up page
                dispose();
                new Signup().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
