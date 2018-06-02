package muyi.base.generics.factory;

import muyi.base.generics.factory.partson.AirFilter;
import muyi.base.generics.factory.partson.FanBelt;
import muyi.base.generics.factory.partson.FuelFilter;
import muyi.base.generics.factory.partson.GeneratorBelt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/20 下午4:00
 * @description:
 */
public class Part {

    static List<Factory<? extends Part>> partFactoryList =
            new ArrayList<Factory<? extends Part>>();

    static {
        partFactoryList.add(new FuelFilter.Factory());
        partFactoryList.add(new AirFilter.Factory());
        partFactoryList.add(new FanBelt.Factory());
        partFactoryList.add(new GeneratorBelt.Factory());
    }

    private static Random random = new Random(47);

    public static Part createRandom() {
        int n = random.nextInt(partFactoryList.size());
        return partFactoryList.get(n).create();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }



}


