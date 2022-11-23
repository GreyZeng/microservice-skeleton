package git.snippets.skeleton.user.filter;

import git.snippets.skeleton.common.vo.User;
import org.springframework.boot.web.reactive.filter.OrderedWebFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class UserFilter implements OrderedWebFilter {


    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println("user service filter");
        HttpHeaders headers = exchange.getRequest().getHeaders();
        Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            System.out.println("key is " + entry.getKey() + "and values are" + entry.getValue());
        }
        // Map<String, String> header = httpRequestToMap(request);
        String userId = headers.getFirst(User.CONTEXT_KEY_USERID);
        return chain.filter(exchange.mutate().request(exchange.getRequest().mutate().header(User.CONTEXT_KEY_USERID, userId).build()).build()).contextWrite(ctx -> ctx.put(User.CONTEXT_KEY_USERID, userId));
    }
}

