package tutorial.core.services.util;

import tutorial.core.models.entities.BlogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public class BlogEntryList {

    private Long blogId;
    private List<BlogEntry> entries = new ArrayList<BlogEntry>();

    public BlogEntryList(Long blogId, List<BlogEntry> entries) {
        this.blogId = blogId;
        this.entries = entries;
    }


    public Long getBlogId() {
        return blogId;
    }
    public void setBlogId(Long blogId) { this.blogId = blogId; }

    public List<BlogEntry> getEntries() {
        return entries;
    }
    public void setEntries(List<BlogEntry> entries) {
        this.entries = entries;
    }
}
