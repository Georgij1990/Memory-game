package newGameWindow;

import mainWindow.MainWindow;
import timer.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Game extends JFrame implements ActionListener {
    private static final ImageIcon image = new ImageIcon("resources/rew1.png");

    public static int val;
    private JPanel jPanel;
    private StopWatch stopWatch;

    public Game(int grid, JLabel jLabel, StopWatch stopWatch) {
        super("Game");
        val = grid;
        this.setSize(setDimension(val));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        this.stopWatch = stopWatch;
        jPanel = new PlayMemory(grid, this, stopWatch);
        add(jPanel, gbc);

        gbc.weighty = 0.02;
        gbc.gridy = 1;

        JPanel panel2 = new JPanel();
        panel2.add(jLabel);
        panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton jButton = new JButton("Exit");
        Font f1  = new Font(Font.DIALOG,  Font.BOLD, 10);
        jButton.setFont(f1);
        jButton.addActionListener(this);
        panel2.add(jButton);
        add(panel2, gbc);

        setResizable(false);

        setVisible(true);

    }

    public Dimension setDimension(int grid) {
        return switch (grid) {
            case 2 -> new Dimension(370, 410);
            case 4 -> new Dimension(750, 950);
            case 6 -> new Dimension(1240, 950);
            default -> throw new IllegalStateException("Unexpected value: " + grid);
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        try {
            new MainWindow();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
