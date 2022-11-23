package git.snippets.skeleton.user.service.impl;


import git.snippets.skeleton.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 */
@Component
public class UserService implements IUserService {

    private final WebClient.Builder loadBalancedWebClientBuilder;

    private final WebClient webClient;

    public UserService(WebClient.Builder loadBalancedWebClientBuilder) {
        this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
        this.webClient = loadBalancedWebClientBuilder.build();
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
    public Flux<String> getProviderData() {
        return webClient.get().uri("http://sc-data-service/getProviderData").retrieve().bodyToFlux(String.class);
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
