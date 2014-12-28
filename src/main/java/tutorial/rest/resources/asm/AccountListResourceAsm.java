package tutorial.rest.resources.asm;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.AccountList;
import tutorial.rest.mvc.AccountController;
import tutorial.rest.resources.AccountListResource;
import tutorial.rest.resources.AccountResource;

import java.util.List;

/**
 * Created by Anika McRae on 28/12/2014.
 *
 * Entity to Resource
 */
public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, tutorial.rest.resources.AccountListResource> {

    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }


    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resources = new AccountResourceAsm().toResources(accountList.getAccounts());

        AccountListResource listResource = new AccountListResource();
        listResource.setAccounts(resources);

        return listResource;
    }
}
