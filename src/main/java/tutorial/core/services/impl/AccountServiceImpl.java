package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;
import tutorial.core.repositories.AccountRepo;
import tutorial.core.repositories.BlogRepo;
import tutorial.core.services.AccountService;
import tutorial.core.services.exceptions.AccountDoesNotExistException;
import tutorial.core.services.exceptions.AccountExistsException;
import tutorial.core.services.exceptions.BlogExistsException;
import tutorial.core.services.util.AccountList;
import tutorial.core.services.util.BlogList;

import java.util.List;

/**
 * Created by Anika McRae on 27/12/2014.
 */

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private BlogRepo blogRepo;



    @Override
    public Account createAccount(Account data) {
        Account account = accountRepo.findAccountByName(data.getName());

        if (account != null) {
            throw new AccountDoesNotExistException();
        }
        //Account must not exist before it is created
        return accountRepo.createAccount(data);
    }



    @Override
    public Account findAccount(Long accountId) {
        return accountRepo.findAccount(accountId);
    }




    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAllAccounts());
    }



    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findAccountByName(name);
    }



    @Override
    public Blog createBlog(Long accountId, Blog data) {
        if (blogRepo.findBlogByTitle(data.getTitle()) != null) {
            throw new BlogExistsException();
        }

        Account account = accountRepo.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }

        Blog blog = blogRepo.createBlog(data);
        blog.setOwner(account);

        return blog;
    }


    @Override
    public BlogList findBlogsByAccount (Long accountId) {
        if (accountRepo.findAccount(accountId) == null) {
            throw new AccountDoesNotExistException();
        }

        return new BlogList(blogRepo.findBlogsByAccount(accountId));
    }
}
