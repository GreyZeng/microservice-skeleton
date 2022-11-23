package git.snippets.skeleton.user.filter;

import git.snippets.skeleton.common.vo.User;
import org.springframework.boot.web.reactive.filter.OrderedWebFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class UserFilter implements OrderedWebFilter {


    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        // Map<String, String> header = httpRequestToMap(request);
        String userId = headers.getFirst(User.CONTEXT_KEY_USERID);
        exchange.getResponse().getHeaders().addAll(exchange.getRequest().getHeaders());
        return chain.filter(exchange).contextWrite(ctx -> ctx.put(User.CONTEXT_KEY_USERID, userId));
    }
}

