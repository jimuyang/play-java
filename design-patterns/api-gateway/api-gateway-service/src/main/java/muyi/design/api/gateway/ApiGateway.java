package muyi.design.api.gateway;

import muyi.design.api.gateway.product.DesktopProduct;
import muyi.design.api.gateway.product.MobileProduct;
import muyi.design.api.gateway.service.ImageService;
import muyi.design.api.gateway.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.crypto.Des;

/**
 * @author: Jimu Yang
 * @date: 2018/11/4 11:02 PM
 * @descricption: want more.
 */
@RestController
public class ApiGateway {

    @Autowired
    private ImageService imageService;

    @Autowired
    private PriceService priceService;

    @GetMapping("/desktop")
    public DesktopProduct getDesktopProduct() {
        DesktopProduct product = new DesktopProduct();
        product.setImagePath(imageService.getImagePath());
        product.setPrice(priceService.getPrice());
        return product;
    }

    @GetMapping("/mobile")
    public MobileProduct getMobileProduct() {
        MobileProduct product = new MobileProduct();
        product.setPrice(priceService.getPrice());
        return product;
    }


}
