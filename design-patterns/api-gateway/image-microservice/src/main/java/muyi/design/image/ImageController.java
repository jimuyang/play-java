package muyi.design.image;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Jimu Yang
 * @date: 2018/11/4 10:53 PM
 * @descricption: want more.
 */
@RestController
public class ImageController {

    @GetMapping("/image-path")
    public String getImagePath() {
        return "/product-image.png";
    }
}
