package git.snippets.skeleton.user.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
public interface IUserService {
    String getDefaultUser();

    Mono<String> getContextUserId();

    //List<String> getProviderData();
    Flux<String> getProviderData();
}
