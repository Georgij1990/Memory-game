package mainWindow;

import highScore.HighScoreHandler;
import highScore.HighScoreWindow;
import highScore.Player;
import highScore.SerializationUtils;
import newGameWindow.NewGameWindow;
import newGameWindow.Rank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainPanel extends JPanel implements ActionListener {

    JFrame jFrame;
    JButton b1;
    JButton b2;
    JButton b3;

    public MainPanel(JFrame jFrame) {
        this.jFrame = jFrame;
        GridLayout gridLayout = new GridLayout(4, 1);
        gridLayout.setVgap(15);
        setLayout(gridLayout);

        try {
            deSerializeAllMaps();//editing step 2
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        JLabel label = new JLabel();
        label.setText("Memory The game");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Font  f1  = new Font(Font.DIALOG,  Font.BOLD, 15);
        label.setFont(f1);

        b1 = new JButton("New Game");
        b2 = new JButton("High Score");
        b3 = new JButton("Exit");

        add(label);
        add(b1);
        b1.addActionListener(this);
        add(b2);
        b2.addActionListener(this);
        add(b3);
        b3.addActionListener(this);

        setBounds(100, 25, 200, 225);

        setVisible(true);

    }

    public JButton getB1() {
        return b1;
    }

    public JButton getB2() {
        return b2;
    }

    public JButton getB3() {
        return b3;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            this.jFrame.dispose();
            NewGameWindow newGameWindow = new NewGameWindow();
        } else if (e.getSource() == b2) {
            jFrame.dispose();
            HighScoreWindow highScoreWindow = new HighScoreWindow();
        } else {
            // editing step 1
            try {
                serializeAllMaps();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(1);
        }
    }

    public static void serializeAllMaps() throws IOException {// editing step 1
        for (int  i = 2; i < 8; i += 2) {
            SerializationUtils.serialize(new ArrayList<>(HighScoreHandler.getTreeMap(i).keySet()), Rank.generateFileName(i));
        }
    }

    // editing step 2
    public static void deSerializeAllMaps() throws IOException, ClassNotFoundException {
        ArrayList<Player> players;
        for (int  i = 2; i < 8; i += 2) {
            players = SerializationUtils.deserialize(Rank.generateFileName(i));
            if (players != null) {
                for (Player player : players) {
                    HighScoreHandler.putIntoTreeMap(i, player, player.getName());
                }
            }
        }
    }
}
