package git.snippets.skeleton.user.service.impl;


import git.snippets.skeleton.user.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 *
 */
@Component
public class UserService implements IUserService {

    private final WebClient webClient;

    public UserService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String getDefaultUser() {
        // TODO
        return "Default User";
    }

    @Override
    public String getContextUserId() {
        // TODO
        return "Context User id";
    }

    @Override
    public List<String> getProviderData() {
        Mono<List> listMono = webClient.get().uri("http://sc-data-service/getProviderData").retrieve().bodyToMono(List.class);
        return listMono.
        List<String> result = webClient.getForObject("http://sc-data-service/getProviderData", List.class);
        return result;
    }
//    @Override
//    public List<String> getProviderData() {
//        return Arrays.asList("a", "b");
//    }

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
}
