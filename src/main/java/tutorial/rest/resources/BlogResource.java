package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import tutorial.core.models.entities.Blog;

/**
 * Created by Anika McRae on 23/12/2014.
 *
 * Converts Resource to Entity.
 */
public class BlogResource extends ResourceSupport {

    private String title;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }


    public Blog toBlog() {
        Blog blog = new Blog();
        blog.setTitle(title);

        return blog;
    }

}
