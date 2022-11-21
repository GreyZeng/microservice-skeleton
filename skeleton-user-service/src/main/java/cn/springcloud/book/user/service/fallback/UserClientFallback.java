package cn.springcloud.book.user.service.fallback;


import cn.springcloud.book.user.service.dataservice.DataService;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class UserClientFallback implements DataService {

    @Override
    public String getDefaultUser() {
        return "get getDefaultUser failed";
    }

    @Override
    public String getContextUserId() {
        return "get getContextUserId failed";
    }

}
