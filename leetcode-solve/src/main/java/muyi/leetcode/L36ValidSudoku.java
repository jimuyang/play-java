package muyi.leetcode;

import org.junit.Test;

/**
 * @author: Jimu Yang
 * @date: 2019/3/17 2:33 AM
 * @descricption: want more.
 * 检查一个数独是否合理
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 */
public class L36ValidSudoku {

    int[][] firstNine = new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};

    @Test
    public void test() {
        char[][] test =
                new char[][]{
                        {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        System.out.println(isValidSudoku(test));

    }


    public boolean isValidSudoku(char[][] board) {
        int temp;
        for (int i = 0; i < 9; i++) {
            int jc = 0, ic = 0;
            for (int j = 0; j < 9; j++) {
                temp = board[i][j] - 48;
                if (temp < 10 && temp >= 0) {
                    temp = jc | 1 << temp;
                    if (temp == jc) {
                        return false;
                    }
                    jc = temp;
                }
                temp = board[j][i] - 48;
                if (temp < 10 && temp >= 0) {
                    temp = ic | 1 << temp;
                    if (temp == ic) {
                        return false;
                    }
                    ic = temp;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int n = 0;
                for (int k = 0; k < 9; k++) {
                    int[] xy = firstNine[k];
                    temp = board[xy[0] + i * 3][xy[1] + j * 3] - 48;
                    if (temp < 10 && temp >= 0) {
                        temp = n | 1 << temp;
                        if (temp == n) {
                            return false;
                        }
                        n = temp;
                    }
                }
            }
        }
        return true;
    }


}
