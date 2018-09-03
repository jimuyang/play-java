package muyi.eight.tutorial;

import muyi.eight.tutorial.Streams;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/3 下午11:56
 * @description:
 */
public class StreamsTest {

    @Test
    public void main() {
        new Streams().main();
    }


    @Test
    public void testParallelStream() {
        new Streams().testParallelStream();
    }

    @Test
    public void testMaps() {
        new Streams().testMaps();
    }
}