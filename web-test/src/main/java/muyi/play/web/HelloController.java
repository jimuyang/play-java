package muyi.play.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yang Fan
 * @date: 2019-05-05
 * @desc:
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

//    @Autowired
//    private HelloService helloService;

    @GetMapping("/name")
    public String hello() {
        SpringBeanUtil.getApplicationContext().getBeansOfType(HelloService.class);
        return "hello world";
    }

}
