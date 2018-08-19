package muyi.information.microservice.controller;

import muyi.information.microservice.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
@RestController
public class InformationController {

    @Autowired
    private InformationService informationService;

    /**
     * Endpoint to retrieve a product's title.
     *
     * @return
     */
    @GetMapping("/information")
    public String getProductTitle() {
        return this.informationService.getInformation();
    }
}
