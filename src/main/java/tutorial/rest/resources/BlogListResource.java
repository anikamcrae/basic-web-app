package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anika McRae on 23/12/2014.
 *
 * Converts Resource to Entity.
 */
public class BlogListResource extends ResourceSupport {

    private List<BlogResource> blogs = new ArrayList<BlogResource>();

    public List<BlogResource> getBlogs() { return blogs; }
    public void setBlogs(List<BlogResource> blogs) { this.blogs = blogs; }


}
