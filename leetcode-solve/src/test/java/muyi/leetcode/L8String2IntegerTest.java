package muyi.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public class L8String2IntegerTest {

    @Test
    public void test() {
        int result = new L8String2Integer().myAtoi("3145 sdfs");
        assert result == 3145;
    }

}