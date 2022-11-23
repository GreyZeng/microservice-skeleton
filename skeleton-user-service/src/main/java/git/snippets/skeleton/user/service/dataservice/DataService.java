//package cn.springcloud.book.user.service.dataservice;
//
//
//import cn.springcloud.book.user.service.fallback.UserClientFallback;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//
//@FeignClient(name = "sc-data-service", fallback = UserClientFallback.class)
//public interface DataService {
//    @RequestMapping(value = "/getDefaultUser", method = RequestMethod.GET)
//    String getDefaultUser();
//
//    @RequestMapping(value = "/getContextUserId", method = RequestMethod.GET)
//    String getContextUserId();
//
//}
package git.snippets.skeleton.user.service.dataservice;


import feign.Headers;
import feign.Param;
import git.snippets.skeleton.common.vo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Headers(User.CONTEXT_KEY_USERID + ": {requester}")
@ReactiveFeignClient(value = "sc-data-service", url = "sc-data-service")
public interface DataService {
    //    @RequestMapping(value = "/user/add", method = GET)
//    String addUser(User user);
//
//    @RequestMapping(value = "/user/update", method = POST)
//    String updateUser(@RequestBody User user);
    @RequestMapping(value = "/getContextUserId", method = GET)
    Mono<String> getContextUserId(@Param("requester") String requester);

//    @RequestMapping(value = "/getDefaultUser", method = GET)
//    String getDefaultUser();
}

