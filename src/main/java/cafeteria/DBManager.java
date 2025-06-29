package cafeteria;

import java.sql.*;

public class DBManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cafeteria_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Ibtisam@321";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

public static boolean addStudent(Student student, String pin) {
    String sql = "INSERT INTO students(name, pin, balance) VALUES (?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, student.getName());
        stmt.setString(2, pin);
        stmt.setDouble(3, student.getBalance());
        stmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public static Student login(String name, String pin) {
        String sql = "SELECT * FROM students WHERE name=? AND pin=?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getString("name"),
                    rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateBalance(Student student) {
        String sql = "UPDATE students SET balance = ? WHERE name = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, student.getBalance());
            stmt.setString(2, student.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
