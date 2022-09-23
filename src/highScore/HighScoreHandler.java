package highScore;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class HighScoreHandler implements Serializable {

    private static final TreeMap<Player, String> grid2by2 = new TreeMap<>(new Comparator<>() {
        @Override
        public int compare(Player o1, Player o2) {
            if (o1.countScores().equals(o2.countScores())) {
                return o1.getSerialNumber().compareTo(o2.getSerialNumber());
            } else {
                return o1.countScores().compareTo(o2.countScores());
            }
        }
    });
    private static final TreeMap<Player, String> grid4by4 = new TreeMap<>(new Comparator<>() {
        @Override
        public int compare(Player o1, Player o2) {
            if (o1.countScores().equals(o2.countScores())) {
                return o1.getSerialNumber().compareTo(o2.getSerialNumber());
            } else {
                return o1.countScores().compareTo(o2.countScores());
            }
        }
    });
    private static final TreeMap<Player, String> grid6by6 = new TreeMap<>(new Comparator<>() {
        @Override
        public int compare(Player o1, Player o2) {
            if (o1.countScores().equals(o2.countScores())) {
                return o1.getSerialNumber().compareTo(o2.getSerialNumber());
            } else {
                return o1.countScores().compareTo(o2.countScores());
            }
        }
    });

    public static void putIntoTreeMap(int grid, Player player, String name) {
        switch (grid) {
            case 2 -> grid2by2.put(player, name);
            case 4 -> grid4by4.put(player, name);
            case 6 -> grid6by6.put(player, name);
            default -> System.out.println("Grid size isn't suitable!");
        }
    }

    public static TreeMap<Player, String> getTreeMap(int grid) {
        return switch (grid) {
            case 2 -> getGrid2by2();
            case 4 -> getGrid4by4();
            case 6 -> getGrid6by6();
            default -> throw new IllegalStateException("Unexpected value: " + grid);
        };
    }

    public static TreeMap<Player, String> getGrid2by2() {
        return grid2by2;
    }

    public static TreeMap<Player, String> getGrid4by4() {
        return grid4by4;
    }

    public static TreeMap<Player, String> getGrid6by6() {
        return grid6by6;
    }

    public static String readFromHighScoresFile(int grid) {
        String str1 = String.format("High scores for %d*%d grid:\n", grid, grid);
        String str2 = returnStringWithPlayersRank(new ArrayList<>(getTreeMap(grid).keySet()));
        return str1 + str2;
    }

    public static String returnStringWithPlayersRank(ArrayList<Player> players) {
        return players.stream()
                .map(player -> {
                    String name = player.getName();
                    String time = player.getTime();
                    String movements = String.valueOf(player.getMovements());
                    return name + " (Time - " + time + ", movements - " + movements + ")\n";
                })
                .collect(Collectors.joining());
    }
}
