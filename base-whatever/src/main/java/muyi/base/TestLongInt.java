package muyi.base;

/**
 * @author: Jimu Yang
 * @date: 2018/12/30 6:12 PM
 * @descricption: want more.
 */
public class TestLongInt {

//    public static void main(String[] args) {
//        Long l = 4956765210L;
//        int i = l.intValue();
//        System.out.println(i);
//    }

    void test() {

        // 赋零值
        int a = 0;
        Object o = null;  // 可以认为null就是类似0x0000的地址

        // 赋值
        a = 1;
        o = new Object(); // 可以认为new返回的就是堆内地址

        // 变量间赋值
        int a1 = a;
        Object o1 = o;    // 将o的"值"(地址)copy给o1

        //

    }


    static void func(int a, Object o) {
        System.out.println();
    }

    public static void main(String[] args) {
        int a = 1;
        Object o = new Object();
        func(a, o);
        func(1, new Object());
    }


}
