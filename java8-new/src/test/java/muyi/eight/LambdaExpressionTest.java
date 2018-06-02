package muyi.eight;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/2 下午1:23
 * @description:
 */
public class LambdaExpressionTest {

    @Test
    public void oldSort() {
        new LambdaExpression().oldSort();
    }

    @Test
    public void sortWithLambda() {
        new LambdaExpression().sortWithLambda();
    }
}