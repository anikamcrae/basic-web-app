package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anika McRae on 28/12/2014.
 */
public class AccountListResource extends ResourceSupport {

    public List<AccountResource> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResource> accounts) {
        this.accounts = accounts;
    }

    private List<AccountResource> accounts = new ArrayList<AccountResource>();

}
