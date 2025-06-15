package cafeteria;
import java.sql.*;

public class DBManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cafeteria_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Ibtisam@321";

    public static void connect() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE IF NOT EXISTS students (name VARCHAR(100), balance DOUBLE)");
            }
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    public static void updateBalance(Student student) {
        String sql = "REPLACE INTO students(name, balance) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setDouble(2, student.getBalance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
