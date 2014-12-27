package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.Account;
import tutorial.mvc.AccountController;
import tutorial.rest.resources.AccountResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
/**
 * Created by Anika McRae on 21/12/2014.
 *
 * Converts Entity to Resource.
 *
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {

    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }


    @Override
    public AccountResource toResource(Account account) {
        AccountResource resource = new AccountResource();
        resource.setName(account.getName());
        resource.setPassword(account.getPassword());

        resource.add(
            linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel()
        );
        return resource;

        /* {
            "name":"test",
                "links":[
                            {
                                "rel":"self",
                                "href":"http://localhost/rest/accounts/1"
                            }
                        ]
        } */

    }
}