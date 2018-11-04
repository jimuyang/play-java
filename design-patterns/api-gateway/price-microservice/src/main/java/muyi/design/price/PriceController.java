package muyi.design.price;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jimu Yang
 * @date: 2018/11/4 10:57 PM
 * @descricption: want more.
 */
@RestController
public class PriceController {

    @GetMapping("/price")
    public String getPrice() {
        return "20";
    }
}
