import mainWindow.MainWindow;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainWindow();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
