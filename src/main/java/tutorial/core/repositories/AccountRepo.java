package tutorial.core.repositories;

import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;

/**
 * Created by Anika McRae on 27/12/2014.
 */
public interface AccountRepo {

    public Account findAccount(Long accountId);
    public Account createAccount(Account account);
    public Blog createBlog(Long accountId, Blog blog);

}
