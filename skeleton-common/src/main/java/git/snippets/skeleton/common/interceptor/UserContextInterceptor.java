//package git.snippets.skeleton.common.interceptor;
//
//import git.snippets.skeleton.common.context.UserContextHolder;
//import git.snippets.skeleton.common.util.HttpConvertUtil;
//import git.snippets.skeleton.common.vo.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class UserContextInterceptor implements HandlerInterceptor {
//    private static final Logger log = LoggerFactory.getLogger(UserContextInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) {
//        User user = new User(HttpConvertUtil.httpRequestToMap(request));
//        if (StringUtils.isEmpty(user.getUserId()) && StringUtils.isEmpty(user.getUserName())) {
//            log.error("the user is null, please access from gateway or check user info");
//            return false;
//        }
//        UserContextHolder.set(user);
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2, ModelAndView arg3) {
//        // DOING NOTHING
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse respone, Object arg2, Exception arg3) {
//        UserContextHolder.shutdown();
//    }
//
//
//}
