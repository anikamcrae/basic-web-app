package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.Account;
import tutorial.core.repositories.AccountRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Anika McRae on 27/12/2014.
 */
@Repository
public class JpaAccountRepo implements AccountRepo {

    @PersistenceContext
    private EntityManager em;



    @Override
    public List<Account> findAllAccounts() {
        Query query = em.createQuery("SELECT a FROM Account a");
        return query.getResultList();
    }



    @Override
    public Account findAccount(Long id) {
        return em.find(Account.class, id);
    }



    @Override
    public Account findAccountByName(String name) {
        Query query = em.createQuery("Select a from Account a where a.name = :name");
        query.setParameter("name", name);

        List<Account> accounts = query.getResultList();

        if (accounts.isEmpty()) {
            return null;
        }

        return accounts.get(0);
    }



    @Override
    public Account createAccount(Account account) {
        em.persist(account);
        return account;
    }


}
