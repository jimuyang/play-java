package muyi.base.newdo;

/**
 * @author: Yang Fan
 * @date: 2019-05-26
 * @desc:
 */
public class Child extends Parent {

    private static int count = 0;

    private String name;

    static {
        System.out.println("child static");
        count = 1;
    }

    // 这样的代码块确实在constructor前
    {
        System.out.println("child nonstatic");
        name = "name set in nonstatic";
    }


    Child() {
        super();

        System.out.println("child constructor");
        System.out.println(name);
        this.name = "child";
    }

    public static void main(String[] args) {
        new Child();
    }


}
