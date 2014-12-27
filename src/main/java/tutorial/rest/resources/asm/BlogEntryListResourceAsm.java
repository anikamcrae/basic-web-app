package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.BlogEntryList;
import tutorial.mvc.BlogController;
import tutorial.rest.resources.BlogEntryListResource;
import tutorial.rest.resources.BlogEntryResource;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Anika McRae on 21/12/2014.
 *
 * Converts Entity to Resource.
 */
public class BlogEntryListResourceAsm extends ResourceAssemblerSupport<BlogEntryList, BlogEntryListResource> {

    public BlogEntryListResourceAsm() {
        super(BlogController.class, BlogEntryListResource.class);
    }



    @Override
    public BlogEntryListResource toResource(BlogEntryList list) {
        List<BlogEntryResource> resources = new BlogEntryResourceAsm().toResources(list.getEntries());

        BlogEntryListResource listResource = new BlogEntryListResource();
        listResource.setEntries(resources);

        listResource.add(
            linkTo(methodOn(BlogController.class).findAllBlogEntries(list.getBlogId())).withSelfRel()
        );

        return listResource;
    }
}
