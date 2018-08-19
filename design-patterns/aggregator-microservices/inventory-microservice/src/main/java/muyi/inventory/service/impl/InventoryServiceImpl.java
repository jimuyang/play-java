package muyi.inventory.service.impl;

import muyi.inventory.service.InventoryService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {


    /**
     * 获得库存
     *
     * @return
     */
    @Override
    public Integer getInventory() {
        return 10;
    }
}
