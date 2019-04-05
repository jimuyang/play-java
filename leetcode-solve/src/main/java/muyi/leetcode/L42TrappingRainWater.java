package muyi.leetcode;

import muyi.leetcode.struct.FixedStack;
import org.junit.Test;

/**
 * @author: Yang Fan
 * @date: 2019-04-04
 * @desc: Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 */
public class L42TrappingRainWater {

    @Test
    public void test() {
        int[] input = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap0(input));
    }

    /**
     * 小明明的算法
     * 一层一层看 除了会超时以外 没啥问题
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int len = height.length;
        int floor = 1;
        int result = 0;

        while (true) {
            int stop = -1;
            int overFloorNum = 0;
            for (int i = 0; i < len; i++) {
                if (height[i] >= floor) {
                    overFloorNum++;
                    if (stop == -1) {
                        stop = i;
                    } else {
                        result += (i - stop - 1);
                        stop = i;
                    }
                }
            }
            if (overFloorNum > 1) {
                floor++;
            } else {
                break;
            }
        }
        return result;
    }

    public int trap0(int[] height) {
        if (height == null || height.length == 0) return 0;

        int len = height.length;
        // 在这个栈中存放的是降序的（栈顶最小）可供选择的左柱
        FixedStack<Item> stack = new FixedStack<>(height.length);
        Item top;

        for (int i = 0; i < len; i++) {
            int hi = height[i];
            if (hi == 0) {
                continue;
            }

            if (stack.isEmpty()) {
                stack.push(new Item(i, hi, 0, 0));
                continue;
            }

            int toccupy = 0;
            // 从栈中找到一个比自己高(或一样高)组成容器
            while (!stack.isEmpty()) {
                top = stack.peek();

                if (stack.size() == 1) {
                    // 只能是你了
                    if (hi <= top.height) {
                        // 组成容器并入栈
                        stack.push(new Item(i, hi, hi * (i - top.index - 1) - toccupy, toccupy + hi));
                    } else {
                        // 组成容器并替换
                        stack.pop();
                        stack.push(new Item(i, hi, top.height * (i - top.index - 1) - toccupy + top.volume, 0));
                    }
                    break;
                }

                if (hi <= top.height) {
                    // 组成容器并入栈
                    stack.push(new Item(i, hi, hi * (i - top.index - 1) - toccupy, toccupy + hi));
                    break;
                } else {
                    // 出栈
                    toccupy += top.occupy;
                    stack.pop();
                }
            }
        }
        // 最后遍历栈来取得结果
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop().volume;
        }
        return result;
    }

//    private static int volume(Item item1, Item item2) {
//        int height = item1.height > item2.height ? item2.height : item1.height;
//        return height * Math.abs(item1.index - item2.index);
//    }


    class Item {

        /**
         * index
         */
        int index;

        /**
         * 高
         */
        int height;

        /**
         * 目前容积
         */
        int volume;

        /**
         * 外壁加内柱总占用
         */
        int occupy;

        public Item() {
        }

        public Item(int index, int height, int volume, int occupy) {
            this.index = index;
            this.height = height;
            this.volume = volume;
            this.occupy = occupy;
        }


    }
}
