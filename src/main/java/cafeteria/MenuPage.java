package cafeteria;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuPage {
    private Student student;
    private JFrame frame;
    private JLabel balanceLabel;

    public MenuPage(Student student) {
        this.student = student;
        showMenu();
    }

    private void showMenu() {
        frame = new JFrame("Cafeteria Menu");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(new Color(255, 250, 240));

        JLabel welcome = new JLabel("Welcome, " + student.getName(), SwingConstants.CENTER);
        balanceLabel = new JLabel("Balance: $" + student.getBalance(), SwingConstants.CENTER);

        JButton buySandwich = new JButton("Buy Sandwich ($5)");
        JButton buyDrink = new JButton("Buy Drink ($2)");
        JButton viewOrders = new JButton("View Order History");
        JButton logoutBtn = new JButton("Logout");

        logoutBtn.setBackground(Color.RED);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);

        panel.add(welcome);
        panel.add(balanceLabel);
        panel.add(buySandwich);
        panel.add(buyDrink);
        panel.add(viewOrders);
        panel.add(logoutBtn);

        frame.add(panel);
        frame.setVisible(true);

        buySandwich.addActionListener(e -> purchaseItem("Sandwich", 5));
        buyDrink.addActionListener(e -> purchaseItem("Drink", 2));
        viewOrders.addActionListener(e -> showOrderHistory());
        logoutBtn.addActionListener(e -> frame.dispose());
    }

    private void purchaseItem(String item, double cost) {
        if (student.getBalance() >= cost) {
            student.setBalance(student.getBalance() - cost);
            student.addOrder(item + " - $" + cost);
            DBManager.updateBalance(student);
            balanceLabel.setText("Balance: $" + student.getBalance());
            JOptionPane.showMessageDialog(frame, "You bought a " + item);
        } else {
            JOptionPane.showMessageDialog(frame, "Insufficient balance.");
        }
    }

    private void showOrderHistory() {
        List<String> orders = student.getOrderHistory();
        StringBuilder msg = new StringBuilder("Order History:\n");
        for (String order : orders) {
            msg.append("- ").append(order).append("\n");
        }
        JOptionPane.showMessageDialog(frame, msg.toString());
    }
}
