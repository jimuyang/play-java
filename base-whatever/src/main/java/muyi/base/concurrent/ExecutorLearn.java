package muyi.base.concurrent;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * @author: Yang Fan
 * @date: 2019/11/1
 * @desc: Java把工作单元和执行机制分开 工作单元：Runnable Callable 执行机制由Executor提供
 */
public class ExecutorLearn {

    /**
     * {@link Executor}
     */

    // 一个串行的Executor


    static class SerialExecutor implements Executor {
        final Queue<Runnable> taskQueue = new ArrayDeque<>();
        final Executor executor;
        Runnable active;

        SerialExecutor(Executor executor) {
            this.executor = executor;
        }

        @Override
        public synchronized void execute(Runnable command) {
            taskQueue.offer(() -> {
                try {
                    executor.execute(command);
                } finally {
                    scheduleNext();
                }
                if (active == null) {
                    scheduleNext();
                }
            });
        }

        protected synchronized void scheduleNext() {
            active = taskQueue.poll();
            if (active != null) {
                executor.execute(active);
            }
        }
    }


}
