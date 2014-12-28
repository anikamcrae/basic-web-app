package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;
import tutorial.core.services.AccountService;
import tutorial.core.services.exceptions.AccountDoesNotExistException;
import tutorial.core.services.exceptions.AccountExistsException;
import tutorial.core.services.exceptions.BlogExistsException;
import tutorial.rest.exceptions.BadRequestException;
import tutorial.rest.exceptions.ConflictException;
import tutorial.rest.resources.AccountResource;
import tutorial.rest.resources.BlogResource;
import tutorial.rest.resources.asm.AccountResourceAsm;
import tutorial.rest.resources.asm.BlogResourceAsm;

import java.net.URI;

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

//    public void setService(AccountService service) { this.service = service; }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccountResource) {
        try {
            Account account = service.createAccount(sentAccountResource.toAccount());

            AccountResource accountResource = new AccountResourceAsm().toResource(account);

            HttpHeaders header = new HttpHeaders();

            header.setLocation(
                    URI.create(accountResource.getLink("self").getHref())
            );

            return new ResponseEntity<AccountResource>(accountResource, header, HttpStatus.CREATED);
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



//    @RequestMapping(value="/{accountId}/blogs", method=RequestMethod.POST)
//    public ResponseEntity<BlogResource> createBlog(@PathVariable Long accountId, @RequestBody BlogResource sentBlogResource) {
//        try {
//            Blog blog = service.createBlog(accountId, sentBlogResource.toBlog());
//
//            BlogResource blogResource = new BlogResourceAsm().toResource(blog);
//
//            //Headers = {Location=[http://localhost/rest/blogs/1], Content-Type=[application/json;charset=UTF-8]}
//            HttpHeaders header = new HttpHeaders();
//            header.setLocation(URI.create(blogResource.getLink("self").getHref()));
//
//            return new ResponseEntity<BlogResource>(blogResource, header, HttpStatus.CREATED);
//        } catch (AccountDoesNotExistException e) {
//            throw new BadRequestException(e);
//        } catch (BlogExistsException e) {
//            throw new ConflictException(e);
//        }
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
//    }

    @RequestMapping(value="/{accountId}/blogs",
            method = RequestMethod.POST)
    public ResponseEntity<BlogResource> createBlog(
            @PathVariable Long accountId,
            @RequestBody BlogResource res)
    {
        try {
            Blog createdBlog = service.createBlog(accountId, res.toBlog());
            BlogResource createdBlogRes = new BlogResourceAsm().toResource(createdBlog);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdBlogRes.getLink("self").getHref()));
            return new ResponseEntity<BlogResource>(createdBlogRes, headers, HttpStatus.CREATED);
        } catch(AccountDoesNotExistException exception)
        {
            throw new BadRequestException(exception);
        } catch(BlogExistsException e)
        {
            throw new ConflictException(e);
        }
    }



}
