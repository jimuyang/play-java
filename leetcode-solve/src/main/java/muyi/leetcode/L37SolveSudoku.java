package muyi.leetcode;

import muyi.leetcode.struct.FixedStack;
import org.junit.Test;

/**
 * @author: Jimu Yang
 * @date: 2019/3/17 3:14 PM
 * @descricption: want more.
 * 数独解法
 */
@SuppressWarnings("ALL")
public class L37SolveSudoku {


    @Test
    public void test() {
        @SuppressWarnings("Duplicates")
        char[][] test =
//                new char{}{}{
//                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

                new char[][]{
                        {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                        {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                        {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                        {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                        {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                        {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                        {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
        this.solveSudoku(test);
    }

    int[][] nineArea = new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};

    public void solveSudoku(char[][] board) {
        Solver solver = new Solver();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                byte temp = (byte) (board[i][j] - 48);
                if (temp > 0 && temp < 10) {
                    solver.put(i, j, temp);
                }
            }
        }
        FixedStack<Point> stack = new FixedStack<>(81);
        Point current = solver.mostLimited;
        byte usedBe = 0;

        while (true) {
            if (current == null) {
                break;
            }

            if (current.limitedCount < 9) {
                byte tryOne = current.oneUnlimited(usedBe);
                if (tryOne == 0) {
                    // 都试过了 只能回溯
                    current = stack.pop();
                    usedBe = current.now;
                    solver.take(current.x, current.y);
                    continue;
                }

                solver.maxLimitedCount = -1;
                solver.mostLimited = null;

                stack.push(current);
                solver.put(current.x, current.y, tryOne);
                current = solver.mostLimited;
                usedBe = 0;
                continue;
            }

            if (current.limitedCount == 9) {
                // 回溯
                current = stack.pop();
                usedBe = current.now;
                solver.take(current.x, current.y);
                continue;
            }

            throw new RuntimeException("not here");
        }
//        solver.showPoints();
//        System.out.println(new L36ValidSudoku().isValidSudoku(solver.toChars()));
        solver.setAnswer(board);
        solver.showChars(board);
    }

    class Solver {
        Point[][] points;
        Point mostLimited;
        byte maxLimitedCount = 0;

        int[][] temp = new int[9][9];

        Solver() {
            points = new Point[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    points[i][j] = new Point(i, j);
                }
            }
        }

        boolean put(int x, int y, byte value) {
            Point target = points[x][y];
            if (target.put(value)) {
                // limit involved
                int xs = x / 3 * 3;
                int ys = y / 3 * 3;

                clearTemp();
                for (int i = 0; i < 9; i++) {
                    temp[i][y] = 1;
                    temp[x][i] = 1;
                    temp[nineArea[i][0] + xs][nineArea[i][1] + ys] = 1;
                }
                temp[x][y] = 0;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (temp[i][j] == 1) {
                            this.limit(points[i][j], value);
                        }
                    }
                }
                return true;
            }
            return false;
        }

        void take(int x, int y) {
            byte now = points[x][y].now;
            points[x][y].take();
            // unlimit involved
            int xs = x / 3 * 3;
            int ys = y / 3 * 3;

            clearTemp();
            for (int i = 0; i < 9; i++) {
                temp[i][y] = 1;
                temp[x][i] = 1;
                temp[nineArea[i][0] + xs][nineArea[i][1] + ys] = 1;
            }
            temp[x][y] = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (temp[i][j] == 1) {
                        this.unlimit(points[i][j], now);
                    }
                }
            }
        }

        private void limit(Point point, byte value) {
            byte limitedCount = point.limit(value);
            if (limitedCount > maxLimitedCount && point.now == 0) {
                maxLimitedCount = limitedCount;
                mostLimited = point;
            }
        }

        private void unlimit(Point point, byte value) {
            point.unlimit(value);
            // 当使用unlimit时直接置空
            mostLimited = null;
            maxLimitedCount = 0;
        }

        private void clearTemp() {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    temp[i][j] = 0;
                }
            }
        }

        public void showPoints() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(points[i][j].now).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
        }

        public char[][] toChars() {
            char[][] chars = new char[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    chars[i][j] = String.valueOf(points[i][j].now).charAt(0);
                }
            }
            return chars;
        }

        public void setAnswer(char[][] target) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (points[i][j].now != 0)
                        target[i][j] = (char) (points[i][j].now + 48);
                    else
                        target[i][j] = (char) (points[i][j].oneUnlimited() + 48);
                }
            }
        }

        public void showChars(char[][] target) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(target[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
        }
    }


    class Point {
        int x, y;
        byte now = 0;
        /**
         * 仅需18bit 每个数字使用2bit
         * 00可以放入;01 10 11受到1，2，3个约束
         */
        private int limited = 0;
        private byte limitedCount = 0;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        byte oneUnlimited(byte start) {
            for (byte i = (byte) (start + 1); i < 10; i++) {
                if (canIPut(i)) {
                    return i;
                }
            }
            return 0;
        }

        byte oneUnlimited() {
            return oneUnlimited((byte) 0);
        }

        boolean canIPut(byte value) {
            return ((3 << ((value - 1) << 1)) & limited) == 0;
        }

        boolean put(byte value) {
            if (canIPut(value)) {
                now = value;
                return true;
            }
            return false;
        }

        void take() {
            now = 0;
        }

        byte limit(byte value) {
            boolean before = canIPut(value);
            limited += (1 << ((value - 1) << 1));
            boolean after = canIPut(value);
            if (before && !after) limitedCount++;
            return now == 0 ? limitedCount : 0;
        }

        byte unlimit(byte value) {
//            if (canIPut(value))
//                throw new RuntimeException("already unlimited: " + value);
            limited -= (1 << ((value - 1) << 1));
            if (canIPut(value)) limitedCount--;
            return now == 0 ? limitedCount : 0;
        }


//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Point point = (Point) o;
//            return x == point.x &&
//                    y == point.y;
//        }
//
//        @Override
//        public int hashCode() {
////            return Objects.hash(x, y);
//            return y * 10 + x;
//        }
    }

}
