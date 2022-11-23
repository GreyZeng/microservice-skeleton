package git.snippets.skeleton.data.filter;

import org.springframework.boot.web.reactive.filter.OrderedWebFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class DataFilter implements OrderedWebFilter {


    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println("data service filter");
        HttpHeaders headers = exchange.getRequest().getHeaders();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            System.out.println("request key is " + entry.getKey() + "and values are" + entry.getValue());
        }
        headers = exchange.getResponse().getHeaders();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            System.out.println("response key is " + entry.getKey() + "and values are" + entry.getValue());
        }
        // Map<String, String> header = httpRequestToMap(request);
        exchange.getResponse().getHeaders().addAll(exchange.getRequest().getHeaders());
        return chain.filter(exchange);
    }
}

