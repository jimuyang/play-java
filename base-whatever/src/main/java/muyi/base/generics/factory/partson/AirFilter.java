package muyi.base.generics.factory.partson;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/20 下午4:04
 * @description:
 */
public class AirFilter extends Filter {

    public static class Factory implements muyi.base.generics.factory.Factory<AirFilter> {
        @Override
        public AirFilter create() {
            return new AirFilter();
        }
    }


}
