package muyi.ambassador;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.xml.ws.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
@Slf4j
public class Client {


    private final ServiceAmbassador serviceAmbassador = new ServiceAmbassador();

    long useService(int value) {
        long result = serviceAmbassador.doRemoteFunction(value);
        log.info("Service result: " + result);
        return result;
    }

    @Test
    public void test() {
        long result = this.useService(100);
        log.info("Test result: " + result);
        result = this.useService(200);
        log.info("Test result: " + result);
    }
}
