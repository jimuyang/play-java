package muyi.leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: muyi-macpro
 * @Time: 2018/5/12 下午5:15
 * @Description:
 */
public class L7ReverseIntegerTest {

    @Test
    public void test() {
        L7ReverseInteger solution = new L7ReverseInteger();
        Assert.assertEquals(solution.reverse(123),321);
        Assert.assertEquals(solution.reverse(-123),-321);
        Assert.assertEquals(solution.reverse(120),21);
        Assert.assertEquals(solution.reverse(Integer.MAX_VALUE),0);

    }

    @Test
    public void int2String() throws Exception{
        String str = Integer.toString(Integer.MAX_VALUE);
        Assert.assertEquals(str, "2147483647");
    }

//    @Test
//    public void string2Int() throws Exception {
//        int x = Integer.parseInt("2147483648");
//        Assert.assertEquals(x, 0);
//    }

    @Test
    public void testOutOfInteger() {
        int result = Integer.MAX_VALUE / 10;

        int tail = 8;
        int newResult = result * 10 + tail;
//        Assert.assertTrue (((newResult - tail) / 10 == result));
    }

}