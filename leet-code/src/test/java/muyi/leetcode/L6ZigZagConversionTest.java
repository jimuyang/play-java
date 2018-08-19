package muyi.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: muyi-macpro
 * @Time: 2018/5/12 下午4:23
 * @Description:
 */
public class L6ZigZagConversionTest {


    @Test
    public void testInitCharArray() {
        char[] chars = new char[10];
        System.out.print(chars[0]);
        char c = '0';
        Assert.assertEquals(chars[0], 0);
    }

    @Test
    public void test() {
        L6ZigZagConversion solution= new L6ZigZagConversion();
        String res = solution.convert2ZigZag("PAYPALISHIRING",3);
        Assert.assertEquals(res, "PAHNAPLSIIGYIR");

        Assert.assertEquals(solution.convert2ZigZag("A", 1), "A");
        Assert.assertEquals(solution.convert2ZigZag("AB", 1), "AB");

    }

    @Test
    public void testAddAdd() {

        //for循环后的 不管是i++还是++i都是在循环最后执行
        for (int i = 0; i < 3; i++) {

        }
    }

}