package muyi.base.newdo;

/**
 * @author: Yang Fan
 * @date: 2019-05-26
 * @desc:
 */
public class Parent {


    // 证实 final常量不会引起类加载
    public static final String CONSTANT = "constant";


    private String name;

    private int age = 50;

    static {
        System.out.println("parent static");
        count = 2;
    }

    private static int count = 1;

    {
        System.out.println("parent nonstatic");
    }

    Parent() {
        System.out.println("parent constructor");
        this.name = "parent";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
