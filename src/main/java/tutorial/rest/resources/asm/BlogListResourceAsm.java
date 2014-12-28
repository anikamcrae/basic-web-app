package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.BlogList;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.resources.BlogListResource;

/**
 * Created by Anika McRae on 23/12/2014.
 *
 * Converts Entity to Resource.
 */
public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAsm() {
        super(BlogController.class, BlogListResource.class);
    }


    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource resource = new BlogListResource();
        resource.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));

        return resource;
    }
}
