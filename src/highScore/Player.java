package highScore;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.TreeMap;

public class Player implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private String name;
        private int grid;
        private String time;
        private int movements;
        private int elapsedTime;
        private int serialNumber;

        public Player(String name, int grid, String time, int movements, int elapsedTime) {
                this.name = name;
                this.grid = grid;
                this.time = time;
                this.movements = movements;
                this.elapsedTime = elapsedTime;
                this.serialNumber = (int) (Math.random() * 1000000);
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getGrid() {
                return grid;
        }

        public void setGrid(int grid) {
                this.grid = grid;
        }

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

        public int getMovements() {
                return movements;
        }

        public void setMovements(int movements) {
                this.movements = movements;
        }

        public Integer getSerialNumber() {
                return serialNumber;
        }

        public void setSerialNumber(int serialNumber) {
                this.serialNumber = serialNumber;
        }

        public Integer countScores() {
                return this.elapsedTime/1000 + this.movements;
        }
}
