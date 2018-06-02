package muyi.base.generics.factory.partson;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/20 下午4:05
 * @description:
 */
public class FanBelt extends Belt {

    public static class Factory implements muyi.base.generics.factory.Factory<FanBelt> {
        @Override
        public FanBelt create() {
            return new FanBelt();
        }
    }

}
