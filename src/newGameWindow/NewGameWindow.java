package newGameWindow;

import timer.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameWindow extends JFrame implements ActionListener {

    private JSpinner jSpinner;
    private SpinnerNumberModel spinnerNumberModel;
    private int val;

    public NewGameWindow() {

        super("New Game");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(300, 180);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton b1 = new JButton("Play");
        b1.addActionListener(this);
        b1.setBounds(110, 100, 80, 30);
        add(b1);

        JLabel label = new JLabel("Select grid size");
        Font f1  = new Font(Font.DIALOG,  Font.BOLD, 15);
        label.setFont(f1);
        label.setBounds(20, 50, 150, 20);
        add(label);

        Integer current = 2;
        Integer min = 2;
        Integer max = 6;
        Integer step = 2;

        spinnerNumberModel = new SpinnerNumberModel(current, min, max, step);

        jSpinner = new JSpinner(spinnerNumberModel);
        jSpinner.setBounds(180, 50, 70, 20);
        add(jSpinner);

        setResizable(false);

        setVisible(true);

    }

    public int getVal() {
        return val;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        val = (Integer) jSpinner.getValue();
        StopWatch stopWatch = new StopWatch();
        JLabel label = stopWatch.getJLabel();
        Game game = new Game(val, label, stopWatch);
        stopWatch.start();
        dispose();
    }
}
