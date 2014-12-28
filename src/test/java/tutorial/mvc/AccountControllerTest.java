package tutorial.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;
import tutorial.core.services.AccountService;
import tutorial.core.services.exceptions.AccountDoesNotExistException;
import tutorial.core.services.exceptions.AccountExistsException;
import tutorial.core.services.exceptions.BlogExistsException;
import tutorial.rest.mvc.AccountController;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.endsWith;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Anika McRae on 26/12/2014.
 */
public class AccountControllerTest {
    @InjectMocks
    private AccountController controller;

    @Mock
    private AccountService service;

    private MockMvc mockMvc;

    private ArgumentCaptor<Account> accountCaptor;

    @Before
    public void setup() {
        if (mockMvc == null) {
            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
            accountCaptor = ArgumentCaptor.forClass(Account.class);
        }
    }


    @Test
    public void getExistingAccount() throws Exception {
        Account foundAccount = new Account();
        foundAccount.setId(1L);
        foundAccount.setName("test");
        foundAccount.setPassword("test");

        when(service.findAccount(1L)).thenReturn(foundAccount);

        //AccountController.getAccount
        mockMvc.perform(get("/rest/accounts/1"))
                .andDo(print())
                .andExpect(jsonPath("$.name", is(foundAccount.getName())))
                .andExpect(jsonPath("$.password", is(nullValue())))
                .andExpect(status().isOk());

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



    @Test
    public void getNonExistingAccount() throws Exception {
        when(service.findAccount(1L)).thenReturn(null);

        //AccountController.getAccount
        mockMvc.perform(get("/rest/accounts/1"))
                .andDo(print())
                .andExpect(status().isNotFound());

//        Status = 404
//        Body =
    }



    @Test
    public void createAccountNonExistingUsername() throws Exception{
        Account account = new Account();
        account.setId(1L);
        account.setName("test");
        account.setPassword("test");

        when(service.createAccount(any(Account.class))).thenReturn(account);

        //AccountController.createAccount
        mockMvc.perform(post("/rest/accounts")
                .content("{\"name\":\"test\", \"password\":\"test\"}") //You need this to 'post' data into the request body.
                .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(header().string("Location", endsWith("/rest/accounts/1")))
                    .andExpect(jsonPath("$.name", is(account.getName())))
                    .andExpect(status().isCreated());

        verify(service).createAccount(accountCaptor.capture());

        String password = accountCaptor.getValue().getPassword();
        assertEquals("test", password);

        //Status = 201 (Created)
        //Headers = {Location=[http://localhost/rest/accounts/1], Content-Type=[application/json;charset=UTF-8]}
        //Body
//        {
//            "name":"test",
//            "links":[
//                       {
//                          "rel":"self",
//                          "href":"http://localhost/rest/accounts/1"
//                       }
//                     ]
//        }

        //Redirected URL = http://localhost/rest/accounts/1
    }



    @Test
    public void createAccountExistingUsername() throws Exception{
        Account account = new Account();
        account.setId(1L);
        account.setName("test");
        account.setPassword("test");

        when(service.createAccount(any(Account.class))).thenThrow(new AccountExistsException());

        //AccountController.createAccount
        mockMvc.perform(
                post("/rest/accounts")
                        .content("{\"name\": \"test\", \"password\": \"test\"}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isConflict());
        //Status = 409
    }




    @Test
    public void createBlogExistingAccount() throws Exception {
        Blog blog = new Blog();
        blog.setId(1L);
        blog.setTitle("Test Title");

        when(service.createBlog(eq(1L), any(Blog.class))).thenReturn(blog);

          //AccountController.createBlog
        mockMvc.perform(post("/rest/accounts/1/blogs").content("{\"title\":\"Test Title\"}").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.title", is("Test Title")))
                //.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blogs/1"))))
                //.andExpect(header().string("Location", Matchers.endsWith("/blogs/1")))
                .andExpect(status().isCreated());

//      Status = 201 (Created)
//      Headers =
//             {
//                 Location=[http://localhost/rest/blogs/1],
//                 Content-Type=[application/json;charset=UTF-8]
//             }
//
//      Body =
//           { "links" : [  { "href" : "http://localhost/rest/blogs/1",
//                            "rel" : "self"
//                          },
//                          { "href" : "http://localhost/rest/blogs/1/entries",
//                            "rel" : "entries"
//                          }
//                       ],
//             "title" : "Test Title"
//           }
//
//    Redirected URL = http://localhost/rest/blogs/1
    }






    @Test
    public void createBlogNonExistingAccount() throws Exception {
        when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new AccountDoesNotExistException());

        //AccountController.createBlog
        mockMvc.perform(
                         post("/rest/accounts/1/blogs")
                            .content("{\"title\": \"Test Title\"}")
                            .contentType(MediaType.APPLICATION_JSON)
                       )
                    .andDo(print())
                    .andExpect(status().isBadRequest());

                //Status = 400 (Bad Request)
    }



    @Test
    public void createBlogExistingBlogName() throws Exception{
        when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new BlogExistsException());

        //AccountController.createBlog
        mockMvc.perform(
                            post("/rest/accounts/1/blogs")
                                    .content("{\"title\": \"Test Title\"}")
                                    .contentType(MediaType.APPLICATION_JSON)
                        )
                    .andDo(print())
                    .andExpect(status().isConflict());
        //Status = 409
    }





}
