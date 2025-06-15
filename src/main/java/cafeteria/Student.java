package cafeteria;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private double balance;
    private List<String> orderHistory;

    public Student(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.orderHistory = new ArrayList<>();
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public List<String> getOrderHistory() { return orderHistory; }
    public void addOrder(String order) { orderHistory.add(order); }
}
