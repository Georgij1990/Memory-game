package highScore;

import mainWindow.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class RankWindow extends JFrame implements ActionListener {

    private HighScoreHandler highScoreHandler;
    private ArrayList<Player> players;
    private String fileName;
    private int grid;

    public RankWindow(HighScoreHandler highScoreHandler, String fileName, int grid) throws IOException, ClassNotFoundException {
        super("Rank");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        this.fileName = fileName;
        this.grid = grid;
        this.highScoreHandler = highScoreHandler;

        JButton b1 = new JButton("Back To Menu");
        Font f1  = new Font(Font.DIALOG,  Font.BOLD, 10);
        b1.setFont(f1);
        b1.addActionListener(this);

        b1.setBounds(140, 180, 120, 30);
        add(b1);

        JTextArea jTextArea = new JTextArea();
        String str = HighScoreHandler.readFromHighScoresFile(grid);
        jTextArea.setEditable(false);
        jTextArea.setText(str);

        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setBounds(5, 0, 376, 180);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.getContentPane().add(scrollPane);
        setResizable(false);

        setVisible(true);
    }

    public void puttAllObjectsIntoTreeMap() {
        for (Player player : players) {
            highScoreHandler.putIntoTreeMap(grid, player, player.getName());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            MainWindow mainWindow = new MainWindow();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        this.dispose();
    }
}
