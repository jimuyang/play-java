package muyi.aggregator.service;

import muyi.aggregator.dto.Product;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
public interface ProductService {
    /**
     * 对微服务的聚合
     * @return
     */
    Product getProduct();
}
