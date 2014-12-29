package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.mvc.BlogEntryController;
import tutorial.rest.resources.BlogEntryResource;
import tutorial.core.models.entities.BlogEntry;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Anika McRae on 18/12/2014.
 *
 * Converts Entity to Resource.
 * ResourceAssemblerSupport<ConvertFrom, ConvertTo>
 */
public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource> {

    public BlogEntryResourceAsm() {
        super(BlogEntryController.class, BlogEntryResource.class);
    }

    @Override
    public BlogEntryResource toResource(BlogEntry blogEntry) {
        BlogEntryResource resource = new BlogEntryResource();
        resource.setTitle(blogEntry.getTitle());
        resource.setContent(blogEntry.getContent());

        Link linkSelf = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();

        resource.add(linkSelf);

        if (blogEntry.getBlog() != null) {
            Link linkBlog = linkTo(BlogController.class).slash(blogEntry.getBlog().getId()).withRel("blog");
            resource.add(linkBlog);
        }

        return resource;

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

    }
}
