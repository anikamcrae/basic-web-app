package tutorial.core.services;

import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;
import tutorial.core.services.util.AccountList;
import tutorial.core.services.util.BlogList;

import java.util.List;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public interface AccountService {

    public Account createAccount(Account data);
    public Account findAccount(Long accountId);
    public AccountList findAllAccounts();
    public Account findByAccountName(String name);

    public Blog createBlog(Long accountId, Blog data);
    public BlogList findBlogsByAccount(Long accountId);

}
