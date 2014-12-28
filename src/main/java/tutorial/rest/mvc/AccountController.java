package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;
import tutorial.core.services.AccountService;
import tutorial.core.services.exceptions.AccountDoesNotExistException;
import tutorial.core.services.exceptions.AccountExistsException;
import tutorial.core.services.exceptions.BlogExistsException;
import tutorial.core.services.util.AccountList;
import tutorial.core.services.util.BlogList;
import tutorial.rest.exceptions.BadRequestException;
import tutorial.rest.exceptions.ConflictException;
import tutorial.rest.exceptions.NotFoundException;
import tutorial.rest.resources.AccountListResource;
import tutorial.rest.resources.AccountResource;
import tutorial.rest.resources.BlogListResource;
import tutorial.rest.resources.BlogResource;
import tutorial.rest.resources.asm.AccountListResourceAsm;
import tutorial.rest.resources.asm.AccountResourceAsm;
import tutorial.rest.resources.asm.BlogListResourceAsm;
import tutorial.rest.resources.asm.BlogResourceAsm;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Anika McRae on 19/12/2014.
 */
@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

    private AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value = "name", required = false) String name) {
        AccountList accounts = null;

        if (name == null) {
            accounts = service.findAllAccounts();
        } else {
            Account account = service.findByAccountName(name);

            if (account == null) {
                accounts = new AccountList(new ArrayList<Account>());
            } else {
                accounts = new AccountList(Arrays.asList(account));
            }
        }

        AccountListResource resource = new AccountListResourceAsm().toResource(accounts);

        return new ResponseEntity<AccountListResource>(resource, HttpStatus.OK);
    }




    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccountResource) {
        try {
            Account account = service.createAccount(sentAccountResource.toAccount());

            AccountResource accountResource = new AccountResourceAsm().toResource(account);

            HttpHeaders headers = new HttpHeaders();

            headers.setLocation(URI.create(accountResource.getLink("self").getHref()));

            return new ResponseEntity<AccountResource>(accountResource, headers, HttpStatus.CREATED);
        } catch (AccountExistsException e) {
            throw new ConflictException(e);
        }
    }



    @RequestMapping(value="/{accountId}", method=RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId) {
        Account account = service.findAccount(accountId);

        if (account != null) {
            AccountResource accountResource = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<AccountResource>(accountResource, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }


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



    @RequestMapping(value="/{accountId}/blogs", method=RequestMethod.POST)
    public ResponseEntity<BlogResource> createBlog(@PathVariable Long accountId, @RequestBody BlogResource sentBlogResource) {
        try {
            Blog blog = service.createBlog(accountId, sentBlogResource.toBlog());

            BlogResource blogResource = new BlogResourceAsm().toResource(blog);

            //Headers = {Location=[http://localhost/rest/blogs/1], Content-Type=[application/json;charset=UTF-8]}
            HttpHeaders header = new HttpHeaders();
            header.setLocation(URI.create(blogResource.getLink("self").getHref()));

            return new ResponseEntity<BlogResource>(blogResource, header, HttpStatus.CREATED);
        } catch (AccountDoesNotExistException e) {
            throw new BadRequestException(e);
        } catch (BlogExistsException e) {
            throw new ConflictException(e);
        }
//
//           { "links" : [  { "href" : "http://localhost/rest/blogs/1",
//                            "rel" : "self"
//                          },
//                          { "href" : "http://localhost/rest/blogs/1/entries",
//                            "rel" : "entries"
//                          }
//                       ],
//             "title" : "Test Title"
//           }
    }



    @RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs(@PathVariable Long accountId) {
        try {
            BlogList blogs = service.findBlogsByAccount(accountId);
            BlogListResource listResource = new BlogListResourceAsm().toResource(blogs);
            return new ResponseEntity<BlogListResource>(listResource, HttpStatus.OK);
        } catch (AccountDoesNotExistException e) {
            throw new NotFoundException(e);
        }
    }



}
