package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by Anika McRae on 23/12/2014.
 *
 * Converts Resource to Entity
 */
public class BlogEntryListResource extends ResourceSupport {

    private List<BlogEntryResource> entries;

    public List<BlogEntryResource> getEntries() { return entries; }
    public void setEntries(List<BlogEntryResource> entries) { this.entries = entries; }

}
