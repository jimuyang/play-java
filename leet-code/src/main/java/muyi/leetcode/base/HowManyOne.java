package muyi.leetcode.base;

import org.junit.Test;

/**
 * @author: Jimu Yang
 * @date: 2019/3/20 11:47 PM
 * @descricption: want more.
 */
public class HowManyOne {

    /**
     * 数字里有多少个1
     *
     * @see Integer#bitCount(int)
     */
    public int countOne(int i) {
//        String str;
//        i = i - ((i >>> 1) & 0x55555555);
//        str = Integer.toBinaryString(i);
//        System.out.println(String.format("%32s", str));
//
//        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
//        str = Integer.toBinaryString(i);
//        System.out.println(String.format("%32s", str));
//
//        i = (i + (i >>> 4)) & 0x0f0f0f0f;
//        str = Integer.toBinaryString(i);
//        System.out.println(String.format("%32s", str));
//
//        i = i + (i >>> 8);
//        str = Integer.toBinaryString(i);
//        System.out.println(String.format("%32s", str));
//
//        i = i + (i >>> 16);
//        str = Integer.toBinaryString(i);
//        System.out.println(String.format("%32s", str));
//
//        return i & 0x3f;

        // 0x55555555: 0101 0101 0101...
        i = i & 0x55555555 + (i >>> 1) & 0x55555555;
        // 0x33333333: 0011 0011 0011...
        i = i & 0x33333333 + (i >>> 2) & 0x33333333;
        // 0x0f0f0f0f: 0000 1111 0000 1111... 这里做了高位清理
        i = (i + i >>> 4) & 0x0f0f0f0f;
        // 因32位int 最多32个1: 0010 0000 六位bit即可表示
        i = i + i >>> 8;
        i = i + i >>> 16;
        // 取最后六位
        return i & 0x3f;
    }


    @Test
    public void test() {
//        System.out.println(countOne(Integer.MAX_VALUE));


        //11111111111111111111111111111110
        //11111111111111111111111111111111
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(-2 >> 1));

    }
}
