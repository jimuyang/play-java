package muyi.design.api.gateway.service.impl;

import muyi.design.api.gateway.service.PriceService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: Jimu Yang
 * @date: 2018/11/4 11:04 PM
 * @descricption: want more.
 */
@Component
public class PriceServiceImpl implements PriceService {

    @Override
    public String getPrice() {

        String response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:50006/price");
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                response = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
