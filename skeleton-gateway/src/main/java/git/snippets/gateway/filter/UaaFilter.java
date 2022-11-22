package git.snippets.gateway.filter;

import git.snippets.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UaaFilter implements GlobalFilter, Ordered {
    private final GatewayProperties mateUaaProperties;

    public UaaFilter(GatewayProperties mateUaaProperties) {
        this.mateUaaProperties = mateUaaProperties;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 如果未启用网关验证，则跳过
        if (!mateUaaProperties.getEnable()) {
            return chain.filter(exchange);
        } else {
            throw new RuntimeException("no auth");
        }
//        System.err.println("getIgnoreUrl:" + mateUaaProperties.getIgnoreUrl());
//
//        //　如果在忽略的url里，则跳过
//        String path = replacePrefix(exchange.getRequest().getURI().getPath());
//        String requestUrl = exchange.getRequest().getURI().getRawPath();
//        if (ignore(path) || ignore(requestUrl)) {
//            return chain.filter(exchange);
//        }
//
//        // 验证token是否有效
//        ServerHttpResponse resp = exchange.getResponse();
//        String headerToken = exchange.getRequest().getHeaders().getFirst(Oauth2Constant.HEADER_TOKEN);
//        if (headerToken == null) {
//            return unauthorized(resp, "没有携带Token信息！");
//        }
//        Claims claims = SecurityUtil.getClaims(headerToken.replace("bearer ", ""));
//        if (claims == null) {
//            return unauthorized(resp, "token已过期或验证不正确！");
//        }
//        return chain.filter(exchange);

    }

    /**
     * 检查是否忽略url
     *
     * @param path 路径
     * @return boolean
     */
    private boolean ignore(String path) {
        return mateUaaProperties.getIgnoreUrl().stream().map(url -> url.replace("/**", "")).anyMatch(path::startsWith);
    }

    /**
     * 移除模块前缀
     *
     * @param path 路径
     * @return String
     */
    private String replacePrefix(String path) {
        if (path.startsWith("/mate")) {
            return path.substring(path.indexOf("/", 1));
        }
        return path;
    }

//    private Mono<Void> unauthorized(ServerHttpResponse resp, String msg) {
//        return ResponseUtil.webFluxResponseWriter(resp, "application/json;charset=UTF-8", HttpStatus.UNAUTHORIZED, msg);
//    }

    @Override
    public int getOrder() {
        return 0;
    }
}
