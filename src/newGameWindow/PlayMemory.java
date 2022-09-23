package newGameWindow;

import timer.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class PlayMemory extends JPanel implements ActionListener {

    private JFrame jFrame;
    private int grid;
    private int movements;
    private int counter;
    private StopWatch stopWatch;

    private ImageIcon image = new ImageIcon("resources/rew3_1.png");
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<JButton> clickedButtons = new ArrayList<>();
    private ArrayList<ImageIcon> icons = new ArrayList<>();
    private ArrayList<ImageIcon> labelsAndIcons = new ArrayList<>();
    private LinkedHashMap<JButton, ImageIcon> buttonIconLinkedHashMap = new LinkedHashMap<>();

    public PlayMemory(int val, JFrame jFrame, StopWatch stopWatch) {
        this.jFrame = jFrame;
        grid = val;
        GridLayout gridLayout = null;
        if (grid < 6) {
            gridLayout = new GridLayout(grid, grid);
        } else if (grid == 6) {
            gridLayout = new GridLayout(4, 9);
        }
        gridLayout.setVgap(5);
        gridLayout.setHgap(5);
        this.setLayout(gridLayout);
        createPanels(grid, this);
        fillLabelsWithImages();
        this.stopWatch = stopWatch;
//        setVisible(true);
    }

    public void createPanels(int grid, JPanel panel) {
        int counter = 1;

        int[][] arr = new int[grid][grid];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                JPanel jPanel = new JPanel(new BorderLayout());

                JButton jButton = new JButton();
                String name = String.valueOf(counter++);
                jButton.setName(name);
                jButton.setLayout(new BorderLayout());

                int offset = jButton.getInsets().left;
                ImageIcon icon = (ImageIcon) resizeIcon(image, jButton.getWidth() - offset, jButton.getHeight() - offset);
                jButton.setIcon(icon);

                jButton.addActionListener(this);
                jPanel.add(jButton);
                panel.add(jPanel);
                buttons.add(jButton);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int matchedPairs = grid * grid / 2;
        movements++;
        JButton jButton = (JButton) e.getSource();
        ImageIcon icon = buttonIconLinkedHashMap.get(jButton);
        jButton.setIcon(icon);
        clickedButtons.add(jButton);
        labelsAndIcons.add(icon);
        if (clickedButtons.size() == 2) {
            if (!compareIcons()) {
                Timer countdown = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (JButton button : clickedButtons) {
                            button.setIcon(image);
                        }
                        clickedButtons.clear();
                    }
                });
                countdown.setRepeats(false);
                countdown.start();
                labelsAndIcons.clear();
            }  else {
                clickedButtons.clear();
                labelsAndIcons.clear();
                ++counter;
            }
        }
        if (counter == matchedPairs) {
            stopWatch.stopTimer();
            Rank rank = new Rank(grid, movements, stopWatch);
            this.jFrame.dispose();
        }

    }

    public boolean compareIcons() {
        for (int i = 0; i < labelsAndIcons.size() - 1; i++) {
            Image image1 = labelsAndIcons.get(i).getImage();
            Image image2 = labelsAndIcons.get(i + 1).getImage();
            if (!image1.equals(image2)) {
                return false;
            }
        }
        return true;
    }

    public void fillListWithImages() {
        for (int i = 1; i <= 18; i++) {
            String str = "resources/".concat(String.valueOf(i)).concat(".jpg");
            ImageIcon icon = new ImageIcon(str);
            icons.add(icon);
        }
    }

    public void fillLabelsWithImages() {
        fillListWithImages();
        Collections.shuffle(buttons);
        int counter = 0;

        int size = buttons.size() / 2;
        for (JButton button : buttons) {
            if (counter < size) {
                buttonIconLinkedHashMap.put(button, icons.get(counter++));
            } else {
                counter = 0;
                buttonIconLinkedHashMap.put(button, icons.get(counter++));
            }
        }
    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
