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
import tutorial.core.models.entities.Blog;
import tutorial.core.models.entities.BlogEntry;
import tutorial.core.services.BlogService;
import tutorial.core.services.exceptions.BlogNotFoundException;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.BlogList;
import tutorial.rest.exceptions.NotFoundException;
import tutorial.rest.resources.BlogEntryListResource;
import tutorial.rest.resources.BlogEntryResource;
import tutorial.rest.resources.BlogListResource;
import tutorial.rest.resources.BlogResource;
import tutorial.rest.resources.asm.BlogEntryListResourceAsm;
import tutorial.rest.resources.asm.BlogEntryResourceAsm;
import tutorial.rest.resources.asm.BlogListResourceAsm;
import tutorial.rest.resources.asm.BlogResourceAsm;

import java.net.URI;

/**
 * Created by Anika McRae on 19/12/2014.
 */
@Controller
@RequestMapping("/rest/blogs")
public class BlogController {

    private BlogService service;

    @Autowired
    public BlogController(BlogService service) {
        this.service = service;
    }




    @RequestMapping(value="/{blogId}",  method = RequestMethod.GET)
    public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId) {
        Blog blog = service.findBlog(blogId);
        BlogResource resource = new BlogResourceAsm().toResource(blog);

        return new ResponseEntity<BlogResource>(resource, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs() {
        BlogList blogList = service.findAllBlogs();
        BlogListResource listResource = new BlogListResourceAsm().toResource(blogList);

        return new ResponseEntity<BlogListResource>(listResource, HttpStatus.OK);
    }



    @RequestMapping(value = "/{blogId}/blog-entries", method = RequestMethod.POST)
    public ResponseEntity<BlogEntryResource> createBlogEntry(@PathVariable Long blogId, @RequestBody BlogEntryResource sentBlogEntryResource) {
        try {
            BlogEntry blogEntry = service.createBlogEntry(blogId, sentBlogEntryResource.toBlogEntry());

            BlogEntryResource resource = new BlogEntryResourceAsm().toResource(blogEntry);

            HttpHeaders header = new HttpHeaders();

            //Location: http://localhost:8080/basic-web-app/rest/blog-entries/1
            header.setLocation(URI.create(resource.getLink("self").getHref()));

            return new ResponseEntity<BlogEntryResource>(resource, header, HttpStatus.CREATED); //Status: 201 Created
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }

//        {
//            "title": "Kitchen Blog Entry",
//                "content": Test Content,
//                "links": [
//            {
//                "rel": "self",
//                    "href": "http://localhost:8080/basic-web-app/rest/blog-entries/1"
//            },
//            {
//                "rel": "blog",
//                    "href": "http://localhost:8080/basic-web-app/rest/blogs/1"
//            }
//            ]
//        }

//      Header
//      Location: http://localhost:8080/basic-web-app/rest/blog-entries/1

//      Status:
//      201 Created

    }



    @RequestMapping(value = "/{blogId}/blog-entries", method = RequestMethod.GET)
    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(@PathVariable Long blogId) {
        try {
            BlogEntryList list = service.findAllBlogEntries(blogId);

            BlogEntryListResource resource = new BlogEntryListResourceAsm().toResource(list);

            return new ResponseEntity<BlogEntryListResource>(resource, HttpStatus.OK); //Status: 200 OK
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }

//        {
//            "entries": [
//            {
//                "title": "Kitchen Blog Entry",
//                    "content": "Test Content",
//                    "links": [
//                {
//                    "rel": "self",
//                        "href": "http://localhost:8080/basic-web-app/rest/blog-entries/1"
//                },
//                {
//                    "rel": "blog",
//                        "href": "http://localhost:8080/basic-web-app/rest/blogs/1"
//                }
//                ]
//            },
//            {
//                "title": "Kitchen Blog Entry2",
//                    "content": "Test Content2",
//                    "links": [
//                {
//                    "rel": "self",
//                        "href": "http://localhost:8080/basic-web-app/rest/blog-entries/2"
//                },
//                {
//                    "rel": "blog",
//                        "href": "http://localhost:8080/basic-web-app/rest/blogs/1"
//                }
//                ]
//            }
//            ],
//            "links": [
//            {
//                "rel": "self",
//                    "href": "http://localhost:8080/basic-web-app/rest/blogs/1/blog-entries"
//            }
//            ]
//        }
    }









}
