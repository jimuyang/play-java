package muyi.pattern.abstractdocument;

import muyi.pattern.abstractdocument.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/6/9 上午12:21
 * @description:
 */
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public App() {
        log.info("Constructing parts and car");

        //汽车本身可以确定拥有属性：model price
        Map<String, Object> carProperties = new HashMap<>();
        carProperties.put(HasModel.PROPERTY, "300SL");
        carProperties.put(HasPrice.PROPERTY, 120000L);

        Map<String, Object> wheelProperties = new HashMap<>();
        wheelProperties.put(HasType.PROPERTY, "wheel");
        wheelProperties.put(HasModel.PROPERTY, "15C");
        wheelProperties.put(HasPrice.PROPERTY, 100L);

        Map<String, Object> doorProperties = new HashMap<>();
        doorProperties.put(HasType.PROPERTY, "door");
        doorProperties.put(HasModel.PROPERTY, "Lambo");
        doorProperties.put(HasPrice.PROPERTY, 300L);

//        log.info("Arrays.asList(map): {}", Arrays.asList(wheelProperties, doorProperties));
        // 这里动态向car添加我们想要的属性
        carProperties.put(HasParts.PROPERTY, Arrays.asList(wheelProperties, doorProperties));

        Car car = new Car(carProperties);

        log.info("Here is our car:");
        log.info("-> model: {}", car.getModel().orElse(""));
        log.info("-> price: {}", car.getPrice().get());
        log.info("-> parts: ");
        car.getParts().forEach(p -> log.info("\t{}/{}/{}", p.getType().get(), p.getModel().get(), p.getPrice().get()));
    }

}
