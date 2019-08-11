package muyi.leetcode;

/**
 * @author: Yang Fan
 * @date: 2019-08-11
 * @desc: N皇后问题2 这里只需要解法个数
 */
public class L52NQueen2 {

    private int count;

    /**
     * 学习大佬 用3个数组巧妙的解决匹配问题
     */
    public int totalNQueens(int n) {

        boolean[] cols = new boolean[n];     // columns   |
        boolean[] d1 = new boolean[2 * n];   // diagonals \
        boolean[] d2 = new boolean[2 * n];   // diagonals /
        digui(0, cols, d1, d2, n);
        return count;
    }

    private void digui(int row, boolean[] cols, boolean[] d1, boolean[] d2, int n) {
        if (row == n) {
            count++;
        }

        for (int col = 0; col < n; col++) {
            int d1b = col - row + n;
            int d2b = col + row;

            if (cols[col] || d1[d1b] || d2[d2b]) {
                continue;
            }

            cols[col] = true;
            d1[d1b] = true;
            d2[d2b] = true;
            digui(row + 1, cols, d1, d2, n);
            cols[col] = false;
            d1[d1b] = false;
            d2[d2b] = false;
        }
    }
}
