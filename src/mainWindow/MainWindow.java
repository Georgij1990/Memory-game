package mainWindow;

import javax.swing.*;
import java.io.IOException;

public class MainWindow{

    MainPanel mainPanel;
    JFrame jFrame;

    public MainWindow() throws IOException, ClassNotFoundException {
        jFrame = new JFrame("Memory The Game");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(400, 300);
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);

        mainPanel = new MainPanel(jFrame);

        jFrame.add(mainPanel);

        jFrame.setResizable(false);

        jFrame.setVisible(true);

    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public JFrame getJFrame() {
        return jFrame;
    }
}
