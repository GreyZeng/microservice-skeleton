package cn.springcloud.book.user.service.impl;


import cn.springcloud.book.user.service.IUserService;
import cn.springcloud.book.user.service.dataservice.DataService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 *
 */
@Component
public class UserService implements IUserService {

    private final DataService dataService;

    private final RestTemplate restTemplate;

    public UserService(@Qualifier("cn.springcloud.book.user.service.dataservice.DataService") DataService dataService, RestTemplate restTemplate) {
        this.dataService = dataService;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getDefaultUser() {
        return dataService.getDefaultUser();
    }

    @Override
    public String getContextUserId() {
        return dataService.getContextUserId();
    }

    @Override
    public List<String> getProviderData() {
        List<String> result = restTemplate.getForObject("http://sc-data-service/getProviderData", List.class);
        return result;
    }
}
