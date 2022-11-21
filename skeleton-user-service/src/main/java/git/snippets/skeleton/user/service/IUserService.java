package git.snippets.skeleton.user.service;


import java.util.List;

/**
 *
 */
public interface IUserService {
    String getDefaultUser();

    String getContextUserId();

    List<String> getProviderData();
}
