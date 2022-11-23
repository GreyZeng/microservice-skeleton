package git.snippets.skeleton.user.service;


import reactor.core.publisher.Flux;

import java.util.List;

/**
 *
 */
public interface IUserService {
    String getDefaultUser();

    String getContextUserId();

    //List<String> getProviderData();
    Flux<String> getProviderData();
}
