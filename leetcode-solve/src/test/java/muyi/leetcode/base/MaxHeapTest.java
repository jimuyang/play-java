package muyi.leetcode.base;

import muyi.leetcode.struct.MaxHeap;
import org.junit.Test;

import java.util.Random;

/**
 * @author: Jimu Yang
 * @date: 2019/3/19 11:33 PM
 * @descricption: want more.
 */
public class MaxHeapTest {

    @Test
    public void add() {

        MaxHeap<Integer> maxHeap = new MaxHeap<>(10);

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(100);
            System.out.println(r);
            maxHeap.add(r);
        }
        System.out.println("---");

        for (; maxHeap.max() != null; System.out.println(maxHeap.poll())) ;

    }


}