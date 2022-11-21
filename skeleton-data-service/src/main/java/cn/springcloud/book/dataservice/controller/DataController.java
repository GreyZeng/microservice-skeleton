package cn.springcloud.book.dataservice.controller;

import cn.springcloud.book.common.context.UserContextHolder;
import cn.springcloud.book.dataservice.config.DataConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhudeming
 */
@RestController
public class DataController {

    private final DataConfig dataConfig;

    public DataController(DataConfig dataConfig) {
        this.dataConfig = dataConfig;
    }

    @GetMapping("/getContextUserId")
    public String getContextUserId() {
        return UserContextHolder.currentUser().getUserId();
    }

    @GetMapping("/getDefaultUser")
    public String getDefaultUser() {
        return dataConfig.getDefaultUser();
    }

    @GetMapping("/getProviderData")
    public List<String> getProviderData() {
        List<String> provider = new ArrayList<String>();
        provider.add("Beijing Company");
        provider.add("Shanghai Company");
        provider.add("Shenzhen Company");
        return provider;
    }

}
