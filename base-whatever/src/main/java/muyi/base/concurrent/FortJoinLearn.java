package muyi.base.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author: Yang Fan
 * @date: 2019/10/28
 * @desc:
 */
public class FortJoinLearn {

    static class CountTask extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 2;

        private int start;

        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            // 如果任务足够小就计算任务
            boolean canCompute = (end - start) < THRESHOLD;
            if (canCompute) {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += i;
                }
                return sum;
            } else {
                // 分割成2个子任务计算
                int mid = (end + start) / 2;
                CountTask task1 = new CountTask(start, mid);
                CountTask task2 = new CountTask(mid, end);
                //
                task1.fork();
                task2.fork();
                // 等待子任务结果
                int r1 = task1.join();
                int r2 = task2.join();
                return r1 + r2;
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 10);
        Future<Integer> future = forkJoinPool.submit(countTask);
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
