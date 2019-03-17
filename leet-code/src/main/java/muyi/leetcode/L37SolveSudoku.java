package muyi.leetcode;

/**
 * @author: Jimu Yang
 * @date: 2019/3/17 3:14 PM
 * @descricption: want more.
 * 数独解法
 */
public class L37SolveSudoku {


    public void solveSudoku(char[][] board) {

    }

    class Point {
        int x;
        int y;
        int area;

//        int[]

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.area = x / 3 + (y / 3) * 3;
        }


    }

}
