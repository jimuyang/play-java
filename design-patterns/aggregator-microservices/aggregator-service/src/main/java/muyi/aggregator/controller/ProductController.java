package muyi.aggregator.controller;

import muyi.aggregator.dto.Product;
import muyi.aggregator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public Product getProduct() {
        return this.productService.getProduct();
    }

}
