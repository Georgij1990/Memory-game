package timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopWatch extends Thread{

    private JLabel jLabel = new JLabel();
    private int elapsedTime = 0;
    private int seconds = 0;
    private int minutes = 0;
    private String secondsString = String.format("%02d", seconds);
    private String minutesString = String.format("%02d", minutes);
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            jLabel.setText(minutesString + ":" + secondsString);

        }
    });

    public StopWatch() {
        jLabel.setText(minutesString + ":" + secondsString);
        jLabel.setFont(new Font(Font.DIALOG,  Font.BOLD, 10));
        jLabel.setHorizontalAlignment(JTextField.CENTER);
        jLabel.setVisible(true);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public JLabel getJLabel() {
        return jLabel;
    }

    public void setJLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }

    public String getSecondsString() {
        return secondsString;
    }

    public void setSecondsString(String secondsString) {
        this.secondsString = secondsString;
    }

    public String getMinutesString() {
        return minutesString;
    }

    public void setMinutesString(String minutesString) {
        this.minutesString = minutesString;
    }

    @Override
    public void run() {
        startTimer();
    }
}
