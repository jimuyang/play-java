package muyi.base.thread;

import muyi.base.util.SleepUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author: Yang Fan
 * @date: 2019-09-02
 * @desc: daemon线程是一种支持线程
 * 当Java虚拟机中不存在非daemon线程时 Java虚拟机将会推出
 */
public class DaemonThread {

    public static void main(String[] args) {

        /**
         * 运行这3行代码 控制台没有任何输出
         * 因为main线程运行结束后 没有非daemon线程 导致jvm退出 然后所有daemon立即终止 * 但finally块并不会被执行
         * 因此：在构建daemon线程时，不能依靠finally块的内容来确保(jvm因没有非daemon线程而退出时的)资源释放和关闭
         */
//        Thread thread = new Thread(new FinallyRunner(), "DaemonRunner");
//        thread.setDaemon(true);
//        thread.start();

        /**
         * 运行这2行代码 控制台打印 thread finally 很好理解
         */
//        Thread thread = new Thread(new FinallyRunner(), "CommonRunner");
//        thread.start();

        /**
         * 直接结束进程时 没有打印出任何内容 说明此时不是通过中断的方式来结束线程
         */
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException ie) {
            System.out.println("main interrupted exception");
        } finally {
            System.out.println("main thread finally");
        }

    }

    static class FinallyRunner implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException ie) {
                System.out.println("interrupted exception");
            } finally {
                System.out.println("thread finally");
            }
        }
    }
}
