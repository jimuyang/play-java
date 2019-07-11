package muyi.play;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author: Yang Fan
 * @date: 2019-07-02
 * @desc:
 */
public class L857 {


    @Test
    public void test() {
        int[] quality = new int[]{10, 20, 5};
        int[] wage = new int[]{70, 50, 30};
        int K = 2;

        System.out.println(mincostToHireWorkers(quality, wage, K));
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        if (n == 0) {
            return 0.0;
        }

        Item[] items = new Item[n];
        PriorityQueue<Item> minHeap = new PriorityQueue<>(n);

        for (int i = 0; i < n; i++) {
            Item item = new Item();
            item.wage = wage[i];
            item.quality = quality[i];
            item.paid = wage[i];
            items[i] = item;
            minHeap.add(item);
        }

        if (K == 1) {
            return minHeap.poll().paid;
        }

        Double minTotal = null;

        // 分别以每一个作为基准
        for (int i = 0; i < n; i++) {
            double total = wage[i];

            int q = quality[i];
            int w = wage[i];

            minHeap.clear();

            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                double t = ((double) items[j].quality) / q * w;
//                items[j].paid = t > items[j].wage ? t : items[j].wage;
                if (t >= items[j].wage) {
                    items[j].paid = t;
                    minHeap.add(items[j]);
                }
            }

            if (K - 1 <= minHeap.size()) {
                for (int j = 0; j < K - 1; j++) {
                    Item min = minHeap.poll();
                    total += min.paid;
                }

                if (minTotal == null || total < minTotal) {
                    minTotal = total;
                }
            }



        }
        return minTotal;
    }

    class Item implements Comparable<Item> {

        double paid;

        int quality;

        int wage;

        @Override
        public int compareTo(Item o) {
            return Double.compare(this.paid, o.paid);
        }
    }


}
