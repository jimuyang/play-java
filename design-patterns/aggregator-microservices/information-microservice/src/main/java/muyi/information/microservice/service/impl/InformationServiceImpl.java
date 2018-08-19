package muyi.information.microservice.service.impl;

import muyi.information.microservice.service.InformationService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Jimu Yang.
 */
@Service("informationService")
public class InformationServiceImpl implements InformationService {


    /**
     * 获得信息
     *
     * @return
     */
    @Override
    public String getInformation() {
        return "This is the information from InformationServiceImpl.class";
    }


}
