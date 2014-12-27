package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;
import tutorial.core.repositories.AccountRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Anika McRae on 27/12/2014.
 */
@Repository
public class JpaAccountRepo implements AccountRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Account findAccount(Long accountId) {
        return em.find(Account.class, accountId);
    }



    @Override
    public Account createAccount(Account account) {
        em.persist(account);
        return account;
    }



    @Override
    public Blog createBlog(Long accountId, Blog blog) {
        return null;
    }
}
