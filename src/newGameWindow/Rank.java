package newGameWindow;

import highScore.*;
import timer.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Rank extends JFrame implements ActionListener {

    private String name;
    private JTextField textField;
    private int grid;
    private int movements;
    private StopWatch stopWatch;

    public Rank(int grid, int movements, StopWatch stopWatch) {
        super("Set name");
        setLocationRelativeTo(null);
        setSize(300, 180);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(null);

        this.grid = grid;
        this.movements = movements;
        this.stopWatch = stopWatch;

        JLabel label = new JLabel();
        label.setText("Your name");
        Font f1  = new Font(Font.DIALOG,  Font.BOLD, 15);
        label.setFont(f1);
        label.setBounds(50, 10, 200, 30);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        this.textField = new JTextField();
        textField.setBounds(50, 50, 200, 30);
        textField.setText("Player 1");
        textField.setFont(f1);
        add(textField);

        JButton button = new JButton();
        button.setText("OK");
        button.setFont(f1);
        button.setBounds(110, 100, 80, 30);
        button.addActionListener(this);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String minutesString = stopWatch.getMinutesString();
        String secondsString = stopWatch.getSecondsString();
        String time = minutesString + ":" + secondsString;
        int elapsedTime = stopWatch.getElapsedTime();

        name = textField.getText();

        Player player = new Player(name, grid, time, movements, elapsedTime);

        String fileName = generateFileName(grid);

        HighScoreHandler highScoreHandler = new HighScoreHandler();
        HighScoreHandler.putIntoTreeMap(grid, player, player.getName());// put new player into tree map after each game

        try {
            RankWindow rankWindow = new RankWindow(highScoreHandler, fileName, grid);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        this.dispose();
    }

    public static String generateFileName(int grid) {
        return String.format("highScore%dby%d.txt", grid, grid);
    }

}
