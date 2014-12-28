package tutorial.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.Blog;
import tutorial.rest.mvc.AccountController;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.resources.BlogResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Anika McRae on 23/12/2014.
 *
 * Converts Entity to Resource.
 */
public class BlogResourceAsm extends ResourceAssemblerSupport<Blog, BlogResource>{

    public BlogResourceAsm() {
        super(BlogController.class, BlogResource.class);
    }


    @Override
    public BlogResource toResource(Blog blog) {
        BlogResource blogResource = new BlogResource();
        blogResource.setTitle(blog.getTitle());

        Link linkSelf = linkTo(BlogController.class).slash(blog.getId()).withSelfRel();
        Link linkEntries = linkTo(BlogController.class).slash(blog.getId()).slash("blog-entries").withRel("entries");

        blogResource.add(linkSelf);
        blogResource.add(linkEntries);

        if (blog.getOwner() != null) {
            Link linkOwner =  linkTo(AccountController.class).slash(blog.getOwner().getId()).withRel("owner");
            blogResource.add(linkOwner);
        }

        return blogResource;

//        {
//            "title": "Kitchen Blog",
//             "links": [
//            {
//                "rel": "self",
//                    "href": "http://localhost:8080/basic-web-app/rest/blogs/1"
//            },
//            {
//                "rel": "entries",
//                    "href": "http://localhost:8080/basic-web-app/rest/blogs/1/entries"
//            },
//            {
//                "rel": "owner",
//                    "href": "http://localhost:8080/basic-web-app/rest/accounts/1"
//            }
//            ]
//        }


    }
}
