package muyi.eight;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/2 下午8:40
 * @description:
 */
public class MethodReferenceTest {

    @Test
    public void test1() {
        new MethodReference().test();
        new MethodReference().test1();
        new MethodReference().test2();
        new MethodReference().test3();
    }
}