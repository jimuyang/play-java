package muyi.play;

import org.junit.Test;

import java.util.Random;

/**
 * @author: Jimu Yang
 * @date: 2019/3/6 11:24 PM
 * @descricption: want more.
 * <p>
 * 蒙特卡洛算法计算 π
 * 原理简单 不再解释
 */
public class CalculatePi {

    public float getPi() {
        Random random = new Random();
        int count = 0;
        for (int i = 0; i < 100000000; i++) {

            // [-1, 1) 的随机数
            double x = random.nextDouble() * 2 - 1;
            double y = random.nextDouble() * 2 - 1;

            if (x * x + y * y <= 1) {
                count++;
            }
        }
        return (float) count * 4 / 100000000;
    }

    @Test
    public void test() {
        System.out.println(getPi());
    }

}
