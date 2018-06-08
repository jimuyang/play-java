package muyi.base.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: muyi-macpro
 * @Time: 2018/3/7 下午9:00
 * @Description:
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        Plate plate = new Plate();

//        for (int i = 0; i < 10; i++) {
//            new Thread(new GetThread(plate)).start();
//            new Thread(new AddThread(plate)).start();
//        }
        new Thread(new GetThread(plate)).start();
        new Thread(new AddThread(plate)).start();

    }
}


/**
 * 装鸡蛋的盘子
 */
class Plate {
    List<Object> eggs = new ArrayList<Object>();

    /**
     * 取鸡蛋
     */
    public synchronized Object getEgg() {
        while (this.eggs.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object egg = this.eggs.get(0);
        this.eggs.clear();// 清空盘子
        this.notify();// 唤醒阻塞队列的某线程到就绪队列
        System.out.println("拿到鸡蛋");
        return egg;
    }

    /**
     * 放鸡蛋
     */
    public synchronized void putEgg(Object egg) {
        while (this.eggs.size() > 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.eggs.add(egg);// 往盘子里放鸡蛋
        this.notify();// 唤醒阻塞队列的某线程到就绪队列
        System.out.println("放入鸡蛋");
    }
}

class AddThread implements Runnable {
    private Plate plate;
    private Object egg = new Object();

    public AddThread(Plate plate) {
        this.plate = plate;
    }

    public void run() {
        while (true) {
            plate.putEgg(egg);
//                try{
//                    Thread.sleep(1000);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
        }
    }
}

class GetThread implements Runnable {
    private Plate plate;

    public GetThread(Plate plate) {
        this.plate = plate;
    }

    public void run() {
        while (true) {
            plate.getEgg();
//                try{
//                    Thread.sleep(1000);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
        }
    }
}



