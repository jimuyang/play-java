package muyi.base.newdo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: Yang Fan
 * @date: 2019-05-26
 * @desc:
 */
public class ChildTest {

    @Test
    public void test() {
        System.out.println(Parent.CONSTANT);

        System.out.println("new start");

        new Child();
    }

}