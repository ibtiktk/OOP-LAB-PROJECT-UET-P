package cafeteria;

import javax.swing.*;
import java.awt.*;

public class Login {
    public Login() {
        JFrame frame = new JFrame("Login / Sign Up");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        JTextField nameField = new JTextField();
        JPasswordField pinField = new JPasswordField();
        JLabel balanceLabel = new JLabel("Initial Balance:");
        JTextField balanceField = new JTextField();
        panel.add(balanceLabel);
        panel.add(balanceField);


        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Sign Up");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("PIN:"));
        panel.add(pinField);
        panel.add(loginBtn);
        panel.add(signupBtn);

        frame.add(panel);
        frame.setVisible(true);

        loginBtn.addActionListener(e -> {
            String name = nameField.getText();
            String pin = new String(pinField.getPassword());
            Student student = DBManager.login(name, pin);
            if (student != null) {
                frame.dispose();
                new MenuPage(student);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.");
            }
        });

signupBtn.addActionListener(e -> {
    String name = nameField.getText();
    String pin = new String(pinField.getPassword());
    String balanceText = balanceField.getText();

    if (name.isEmpty() || pin.isEmpty() || balanceText.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "All fields are required.");
        return;
    }

    double balance;
    try {
        balance = Double.parseDouble(balanceText);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(frame, "Enter a valid number for balance.");
        return;
    }

    Student student = new Student(name, balance);
    boolean success = DBManager.addStudent(student, pin);

    if (success) {
        JOptionPane.showMessageDialog(frame, "Account created. Please login.");
        frame.dispose();
        new Login(); // if you have a login page class
    } else {
        JOptionPane.showMessageDialog(frame, "Signup failed.");
    }
});
loginBtn.addActionListener(e -> {
    String name = nameField.getText();
    String pin = new String(pinField.getPassword());

    Student student = DBManager.login(name, pin);

    if (student != null) {
        JOptionPane.showMessageDialog(frame, "Login successful!");
        frame.dispose();
        new MenuPage(student);
    } else {
        JOptionPane.showMessageDialog(frame, "Invalid login.");
    }
});

    } } 

