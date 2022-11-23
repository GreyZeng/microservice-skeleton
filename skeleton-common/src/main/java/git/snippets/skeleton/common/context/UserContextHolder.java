package git.snippets.skeleton.common.context;

import git.snippets.skeleton.common.vo.User;

public class UserContextHolder {

    public static ThreadLocal<User> context = new ThreadLocal<>();

    public static User currentUser() {
        return context.get();
    }

    public static void set(User user) {
        context.set(user);
    }

    public static void shutdown() {
        context.remove();
    }

}
