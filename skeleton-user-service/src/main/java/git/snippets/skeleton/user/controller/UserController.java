package git.snippets.skeleton.user.controller;


import git.snippets.skeleton.user.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@RestController
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 获取配置文件中系统默认用户
     *
     * @return
     */
    @GetMapping("/getDefaultUser")
    public String getDefaultUser() {
        return userService.getDefaultUser();
    }

    /**
     * 获取上下文用户
     *
     * @return
     */
    @GetMapping("/getContextUserId")
    public Mono<String> getContextUserId() {
        return userService.getContextUserId();
        // return Mono.deferContextual(ctx -> ctx.get(User.CONTEXT_KEY_USERID));
        //return Mono.subscriberContext().map(x -> x.get(User.CONTEXT_KEY_USERID));
    }

    /**
     * 获取供应商数据
     *
     * @return
     */
    @GetMapping("/getProviderData")
    public Flux<String> getProviderData() {
        return userService.getProviderData();
    }
}
