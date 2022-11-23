package git.snippets.skeleton.data.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController {

    //    private final DataConfig dataConfig;
//
//    public DataController(DataConfig dataConfig) {
//        this.dataConfig = dataConfig;
//    }
//
//    @GetMapping("/getContextUserId")
//    public String getContextUserId() {
//        return UserContextHolder.currentUser().getUserId();
//    }
//
//    @GetMapping("/getDefaultUser")
//    public String getDefaultUser() {
//        return dataConfig.getDefaultUser();
//    }
//
    @GetMapping("/getProviderData")
    public Flux<String> getProviderData() {
        List<String> provider = new ArrayList<>();
        provider.add("Beijing Company");
        provider.add("Shanghai Company");
        provider.add("Shenzhen Company");
        return Flux.fromIterable(provider);
    }


}
