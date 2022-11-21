package cn.springcloud.book.user.service.impl;


import cn.springcloud.book.user.service.IUserService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Component
public class UserService implements IUserService {
    @Override
    public String getDefaultUser() {
        return "Default User";
    }

    @Override
    public String getContextUserId() {
        return "Context User id";
    }

    @Override
    public List<String> getProviderData() {
        return Arrays.asList("a", "b");
    }

//    private final DataService dataService;
//
//    private final RestTemplate restTemplate;
//
//    public UserService(@Qualifier("cn.springcloud.book.user.service.dataservice.DataService") DataService dataService, RestTemplate restTemplate) {
//        this.dataService = dataService;
//        this.restTemplate = restTemplate;
//    }
//
//    @Override
//    public String getDefaultUser() {
//        return dataService.getDefaultUser();
//    }
//
//    @Override
//    public String getContextUserId() {
//        return dataService.getContextUserId();
//    }
//
//    @Override
//    public List<String> getProviderData() {
//        List<String> result = restTemplate.getForObject("http://sc-data-service/getProviderData", List.class);
//        return result;
//    }
}
