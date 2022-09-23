package highScore;

import mainWindow.MainPanel;
import mainWindow.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HighScoreWindow extends JFrame implements ActionListener {

    private JTextArea jTextArea;
    private JButton b1;
    private JButton b2;
    private JButton b4;
    private JButton b6;

    private JSpinner jSpinner;
    private SpinnerNumberModel spinnerNumberModel;
    private int val = 2;
    private String text;

    public HighScoreWindow() {
        super("High Scores");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        try {
            MainPanel.deSerializeAllMaps();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        JPanel jPanel = new JPanel(new FlowLayout());
        addButtonsToPanel(jPanel);
        jPanel.setBounds(10, 180, 380, 70);
        add(jPanel);

        jTextArea = new JTextArea();
        text = HighScoreHandler.readFromHighScoresFile(val);
        jTextArea.setEditable(false);
        jTextArea.setText(text);

        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setBounds(5, 0, 376, 180);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.getContentPane().add(scrollPane);

        setResizable(false);

        setVisible(true);

    }

    public void addButtonsToPanel(JPanel jPanel) {
        Font f1  = new Font(Font.DIALOG,  Font.BOLD, 10);
        b2 = new JButton("2*2");
        b2.setFont(f1);
        b2.addActionListener(this);
        jPanel.add(b2);
        b4 = new JButton("4*4");
        b4.setFont(f1);
        b4.addActionListener(this);
        jPanel.add(b4);
        b6 = new JButton("6*6");
        b6.setFont(f1);
        b6.addActionListener(this);
        jPanel.add(b6);
        b1 = new JButton("Back");
        b1.setFont(f1);
        b1.addActionListener(this);
        jPanel.add(b1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                MainWindow mainWindow = new MainWindow();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            dispose();
        } else if (e.getSource() == b2) {
            text = HighScoreHandler.readFromHighScoresFile(2);
            jTextArea.setText(text);
        } else if (e.getSource() == b4 ) {
            text = HighScoreHandler.readFromHighScoresFile(4);
            jTextArea.setText(text);
        } else if (e.getSource() == b6) {
            text = HighScoreHandler.readFromHighScoresFile(6);
            jTextArea.setText(text);
        }
    }
}
