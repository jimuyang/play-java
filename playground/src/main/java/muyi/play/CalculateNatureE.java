package muyi.play;

import org.junit.Test;

import java.util.Random;

/**
 * @author: Jimu Yang
 * @date: 2019/3/6 11:45 PM
 * @descricption: want more.
 * <p>
 * 利用蒙特卡洛算法 计算自然常数 e
 * <p>
 * f(x) = 1 / x
 * S 为函数f(x) 在[1, 2]上的积分
 * 由牛顿-莱布尼茨公式 S = ln2 - ln1 = ln2
 * 由蒙特卡洛模拟
 */
public class CalculateNatureE {

    public double getE(int n) {
        int count = 0;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            // 区域 x[1,2] y[0,1]内随机点
            double x = random.nextDouble() + 1;
            double y = random.nextDouble();
            if (y <= 1 / x) {
                count++;
            }
        }
        // ln2 = (count / n) * 1
        // e = 2 ^ (n / count)
        return Math.pow(2.0, (double) n / count);
    }


    @Test
    public void test() {
        System.out.println(getE(100000000));
    }
}
