package muyi.base.generics.factory.partson;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/20 下午4:02
 * @description:
 */
public class FuelFilter extends Filter {

    public static class Factory implements muyi.base.generics.factory.Factory<FuelFilter> {
        @Override
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}
