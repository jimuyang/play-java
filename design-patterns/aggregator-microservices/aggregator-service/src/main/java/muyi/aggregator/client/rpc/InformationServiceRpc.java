package muyi.aggregator.client.rpc;

import muyi.aggregator.client.InformationService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
@Component
public class InformationServiceRpc implements InformationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InformationServiceRpc.class);

    @Override
    public String getInformation() {
        String response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:51515/information");
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                response = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException e) {
            LOGGER.error("Exception caught.", e);
        }
        return response;
    }
}
