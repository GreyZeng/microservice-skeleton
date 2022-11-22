package git.snippets.skeleton.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController2 {
    @RequestMapping("who")
    public String who() {
        return "lily";
    }
}
