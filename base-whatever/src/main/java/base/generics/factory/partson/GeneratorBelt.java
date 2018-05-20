package base.generics.factory.partson;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/20 下午4:06
 * @description:
 */
public class GeneratorBelt extends Belt {
    public static class Factory implements base.generics.factory.Factory<GeneratorBelt> {
        @Override
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }

}
