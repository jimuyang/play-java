package muyi.leetcode;

/**
 * @author: Jimu Yang
 * @date: 2019/3/17 3:14 PM
 * @descricption: want more.
 * 数独解法
 */
public class L37SolveSudoku {


    public void solveSudoku(char[][] board) {

//        Point[][]


    }

    class Solver {
        Point[][] points = new Point[9][9];

        void put(int x, int y, byte value) {
            if (points[x][y].now != 0)
                throw new RuntimeException("now is" + points[x][y]);
            points[x][y].now = value;
            // update involved
            for (int i = 0; i < 9; i++) {
                if (points[x][i].now == 0)

            }
        }
    }


    class Point {
        byte now = 0;
        // 仅需9bit 为1代表该位数字不可放入了
        short cannotbe = 0;
        byte num = 0;

        boolean canPut(byte value) {
            return !(now != 0 || (cannotbe & (1 << (value - 1))) == cannotbe);
        }

        void put(byte value) {
            now = value;
        }

        void cannotbe(byte value) {
            cannotbe |= (1 << (value - 1));
        }
    }

}
