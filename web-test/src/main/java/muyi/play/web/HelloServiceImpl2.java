package muyi.play.web;

import org.springframework.stereotype.Service;

/**
 * @author: Yang Fan
 * @date: 2019-05-05
 * @desc:
 */
@Service("helloService2")
public class HelloServiceImpl2 extends AbstractHelloService {

    @Override
    public String sayHi(String name) {
        return "hi, " + name;
    }
}
