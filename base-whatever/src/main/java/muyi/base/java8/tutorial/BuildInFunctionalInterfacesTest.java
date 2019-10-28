package muyi.base.java8.tutorial;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/3 下午1:51
 * @description:
 */
public class BuildInFunctionalInterfacesTest {

    @Test
    public void testPredicate() {
        new BuildInFunctionalInterfaces().testPredicate();
    }

    @Test
    public void testFunction() {
        new BuildInFunctionalInterfaces().testFunction();
    }

    @Test
    public void testSuppliers() {
        new BuildInFunctionalInterfaces().testSuppliers();
    }

    @Test
    public void testConsumers() {
        new BuildInFunctionalInterfaces().testConsumers();
    }

    @Test
    public void testComparators() {
        new BuildInFunctionalInterfaces().testComparators();
    }
}