package git.snippets.skeleton.gateway.filter;

import git.snippets.skeleton.common.exception.BaseException;
import git.snippets.skeleton.common.exception.BaseExceptionBody;
import git.snippets.skeleton.common.vo.User;
import git.snippets.skeleton.gateway.config.UaaProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.alibaba.fastjson.JSON.toJSON;
import static git.snippets.skeleton.common.exception.CommonError.AUTH_EMPTY_ERROR;

@Component
@EnableConfigurationProperties(UaaProperties.class)
public class GatewayFilter implements GlobalFilter, Ordered {
    private final UaaProperties gatewayProperties;

    public GatewayFilter(UaaProperties gatewayProperties) {
        this.gatewayProperties = gatewayProperties;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 如果未启用网关验证，则跳过
        if (Boolean.FALSE.equals(gatewayProperties.getEnable())) {
            return chain.filter(exchange);
        }
        HttpHeaders headers = exchange.getRequest().getHeaders();
        // Map<String, String> header = httpRequestToMap(request);
        String userId = headers.getFirst(User.CONTEXT_KEY_USERID);
        if (StringUtils.isEmpty(userId)) {
            System.out.println("get user id " + userId);
            try {
                BaseException BaseException = new BaseException(AUTH_EMPTY_ERROR.getCode(), AUTH_EMPTY_ERROR.getCodeEn(), AUTH_EMPTY_ERROR.getMessage(), 1L);
                BaseExceptionBody errorBody = new BaseExceptionBody(BaseException);
//                ctx.setSendZuulResponse(false);
//                ctx.setResponseStatusCode(401);
//                ctx.setResponseBody(JSONObject.toJSON(errorBody).toString());
                return unauthorized(exchange.getResponse(), toJSON(errorBody).toString());
            } catch (Exception e) {
                // logger.error("println message error", e);
                throw new RuntimeException("println message error " + e.getMessage());
            }
            // return chain.filter(exchange);
        } else {
            // TODO
            // 把 header 下发到服务提供者
//            for (Map.Entry<String, String> entry : header.entrySet()) {
//                ctx.addZuulRequestHeader(entry.getKey(), entry.getValue());
//            }

            exchange.getResponse().getHeaders().addAll(exchange.getRequest().getHeaders());
            return chain.filter(exchange).contextWrite(ctx -> ctx.put(User.CONTEXT_KEY_USERID, userId));
        }
        //如果在忽略的url里，则跳过
//        String path = replacePrefix(exchange.getRequest().getURI().getPath());
//        String requestUrl = exchange.getRequest().getURI().getRawPath();
//        if (ignore(path) || ignore(requestUrl)) {
//            return chain.filter(exchange);
//        }

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

//    private static Map<String, String> httpRequestToMap(HttpServletRequest request) {
//        Enumeration<String> headerNames = request.getHeaderNames();
//        Map<String, String> headers = new HashMap<>();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            headers.put(headerName, request.getHeader(headerName));
//        }
//        return headers;
//    }

//    public static void authUser(RequestContext ctx) {
//        HttpServletRequest request = ctx.getRequest();
//        Map<String, String> header = httpRequestToMap(request);
//        String userId = header.get(User.CONTEXT_KEY_USERID);
//        if (StringUtils.isEmpty(userId)) {
//            try {
//                BaseException BaseException = new BaseException(AUTH_EMPTY_ERROR.getCode(), AUTH_EMPTY_ERROR.getCodeEn(), AUTH_EMPTY_ERROR.getMessage(), 1L);
//                BaseExceptionBody errorBody = new BaseExceptionBody(BaseException);
//                ctx.setSendZuulResponse(false);
//                ctx.setResponseStatusCode(401);
//                ctx.setResponseBody(JSONObject.toJSON(errorBody).toString());
//            } catch (Exception e) {
//                logger.error("println message error", e);
//            }
//        } else {
//            for (Map.Entry<String, String> entry : header.entrySet()) {
//                ctx.addZuulRequestHeader(entry.getKey(), entry.getValue());
//            }
//        }
//    }

//    /**
//     * 检查是否忽略url
//     *
//     * @param path 路径
//     * @return boolean
//     */
//    private boolean ignore(String path) {
//        return gatewayProperties.getIgnoreUrl().stream().map(url -> url.replace("/**", "")).anyMatch(path::startsWith);
//    }
//
//    /**
//     * 移除模块前缀
//     *
//     * @param path 路径
//     * @return String
//     */
//    private String replacePrefix(String path) {
//        if (path.startsWith("/mate")) {
//            return path.substring(path.indexOf("/", 1));
//        }
//        return path;
//    }

    private Mono<Void> unauthorized(ServerHttpResponse resp, String msg) {
        return ResponseUtil.webFluxResponseWriter(resp, "application/json;charset=UTF-8", HttpStatus.UNAUTHORIZED, msg);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

