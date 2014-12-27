package tutorial.core.services;

import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public interface AccountService {

    public Account findAccount(Long accountId);
    public Account createAccount(Account account);
    public Blog createBlog(Long accountId, Blog blog);

}
