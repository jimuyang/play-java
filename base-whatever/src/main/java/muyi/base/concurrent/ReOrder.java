package muyi.base.concurrent;

/**
 * @author: Yang Fan
 * @date: 2019-07-31
 * @desc: 输出了这一结果，并不代表一定发生了指令重排序，内存可见性问题也会导致这样的输出，
 */
public class ReOrder {

    int x = 0;
    int y = 0;
    int a = 0;
    int b = 0;

    public void run() throws Exception {
        Thread one = new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = 1;
            x = b;
        });
        Thread two = new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b = 1;
            y = a;
        });
        one.start();
        two.start();

        one.join();
        two.join();
        if (x == 0 && y == 0) {
            throw new Exception("不应该啊");
        }
    }


    public static void main(String[] args) throws Exception {
        int c = 0;
        while (true) {
            try {
                new ReOrder().run();
            } catch (Exception e) {
                System.out.println(c);
                throw e;
            }
            c++;
        }
    }


}
