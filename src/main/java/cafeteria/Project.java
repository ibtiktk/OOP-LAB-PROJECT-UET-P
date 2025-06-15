package cafeteria;
public class Project {
    public static void main(String[] args) {
        DBManager.connect();
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MenuPage(new Student("Alice", 100.0));
        });
    }
}
