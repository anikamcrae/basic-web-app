package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.Account;
import tutorial.rest.mvc.AccountController;
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
        resource.setPassword(account.getPassword()); //won't appear in response json because of AccountResource.getPassword @JsonIgnore

        resource.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        resource.add(linkTo(methodOn(AccountController.class).findAllBlogs(account.getId())).withRel("blogs"));

        return resource;

//        {
//            "name": "test",
//                "links": [
//            {
//                "rel": "self",
//                    "href": "http://localhost:8080/basic-web-app/rest/accounts/1"
//            },
//            {
//                "rel": "blogs",
//                    "href": "http://localhost:8080/basic-web-app/rest/accounts/1/blogs"
//            }
//            ]
//        }

    }
}
