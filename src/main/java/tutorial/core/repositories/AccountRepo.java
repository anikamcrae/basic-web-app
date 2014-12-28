package tutorial.core.repositories;

import tutorial.core.models.entities.Account;


import java.util.List;

/**
 * Created by Anika McRae on 27/12/2014.
 */
public interface AccountRepo {

    public List<Account> findAllAccounts();

    public Account findAccount(Long id);

    public Account findAccountByName(String name);

    public Account createAccount(Account account);

}
