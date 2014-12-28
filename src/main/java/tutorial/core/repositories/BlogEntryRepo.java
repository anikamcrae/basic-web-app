package tutorial.core.repositories;

import tutorial.core.models.entities.BlogEntry;

import java.util.List;

/**
 * Created by Anika McRae on 27/12/2014.
 */
public interface BlogEntryRepo {

    public BlogEntry createBlogEntry(BlogEntry data);
    public BlogEntry deleteBlogentry(Long id);
    public BlogEntry updateBlogEntry(Long id, BlogEntry data);
    public BlogEntry findBlogEntry(Long id);
    public List<BlogEntry> findByBlogId(Long blogId);

}
