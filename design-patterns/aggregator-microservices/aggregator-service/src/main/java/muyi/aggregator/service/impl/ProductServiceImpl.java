package muyi.aggregator.service.impl;

import muyi.aggregator.client.InformationService;
import muyi.aggregator.client.InventoryService;
import muyi.aggregator.dto.Product;
import muyi.aggregator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private InformationService informationService;

    @Autowired
    private InventoryService inventoryService;


    /**
     * 对微服务的聚合
     * @return
     */
    @Override
    public Product getProduct() {
        Product product = new Product();
        product.setInventory(this.inventoryService.getInventory());
        product.setTitle(this.informationService.getInformation());
        return product;
    }
}
