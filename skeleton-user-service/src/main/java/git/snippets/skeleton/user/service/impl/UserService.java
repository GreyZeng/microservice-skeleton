package git.snippets.skeleton.user.service.impl;


import git.snippets.skeleton.user.service.IUserService;
import git.snippets.skeleton.user.service.dataservice.DataService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@Component
public class UserService implements IUserService {
    private final DataService dataService;
    private final WebClient webClient;

    public UserService(WebClient.Builder loadBalancedWebClientBuilder, DataService dataService) {
//        this.webClient = loadBalancedWebClientBuilder.filter((clientRequest, exchangeFunction) -> Mono.subscriberContext().flatMap(context -> {
//            String customHeader = context.get(User.CONTEXT_KEY_USERID);
//            ClientRequest clientReq = ClientRequest.from(clientRequest).header(User.CONTEXT_KEY_USERID, customHeader).build();
//
//            return exchangeFunction.exchange(clientReq);
//        })).build();
        this.webClient = loadBalancedWebClientBuilder.build();

        this.dataService = dataService;
    }

    @Override
    public String getDefaultUser() {
        // TODO
        return "Default User";
    }

    @Override
    public Mono<String> getContextUserId() {
        // return ReactiveRequestContextHolder.getRequest().map(request -> request.getHeaders().getFirst(User.CONTEXT_KEY_USERID));
        return dataService.getContextUserId();
    }

    @Override
    public Flux<String> getProviderData() {
        return webClient.get().uri("http://sc-data-service/getProviderData").retrieve().bodyToFlux(String.class);
    }
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
