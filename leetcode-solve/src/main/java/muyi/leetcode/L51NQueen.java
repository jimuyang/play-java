package muyi.leetcode;

import muyi.leetcode.struct.FixedStack;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author: Yang Fan
 * @date: 2019-07-11
 * @desc:
 */
public class L51NQueen {


    @Test
    public void test() {
        List<List<String>> result = this.solveNQueens(4);
        result.forEach(System.out::println);
    }

    /**
     * 经典回溯
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();

        FixedStack<Coord> stack = new FixedStack<>(n);
//        stack.push(new Coord(0, 0));

        int lastX = -1;
        while (!(stack.isEmpty() && lastX == n - 1)) {
            if (stack.isEmpty()) {
                stack.push(new Coord(lastX + 1, 0));
                continue;
            }

            Coord top = stack.peek();
            if (top.y == n - 1) {
                // find one
                List<String> oneResult = new ArrayList<>(n);
                for (int i = 0; i < stack.size(); i++) {
                    Coord coord = stack.valueAt(i);
                    oneResult.add(build(n, coord.x));
                }
                result.add(oneResult);

                // 回溯
                top = stack.pop();
                lastX = top.x;
                continue;

            }
            int y = top.y + 1;

            boolean canPut = false;
            for (int i = lastX + 1; i < n; i++) {
                // 和之前的都不冲突
                boolean unconflict = true;
                for (int j = 0; j < stack.size(); j++) {
                    Coord coord = stack.valueAt(j);
                    if (conflict(coord.x, coord.y, i, y)) {
                        unconflict = false;
                        break;
                    }
                }

                if (unconflict) {
                    canPut = true;
                    stack.push(new Coord(i, y));
                    lastX = -1;
                    break;
                }
            }

            // 已经没有合适的位置 回溯
            if (!canPut) {
                top = stack.pop();
                lastX = top.x;
            }
        }

        return result;
    }

    private String build(int n, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i == index) {
                sb.append("Q");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private boolean conflict(Coord coord1, Coord coord2) {
        return conflict(coord1.x, coord1.y, coord2.x, coord2.y);
    }

    private boolean conflict(int x1, int y1, int x2, int y2) {
        return x1 == x2
                || y1 == y2
                || Math.abs((y1 - y2) / (x1 - x2)) == 1;
    }


    class Coord {
        int x;
        int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
