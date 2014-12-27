package tutorial.core.services;

import tutorial.core.models.entities.Blog;
import tutorial.core.models.entities.BlogEntry;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.BlogList;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public interface BlogService {

    public Blog findBlog(Long blogId);
    public BlogList findAllBlogs();

    public BlogEntry createBlogEntry(Long blogId, BlogEntry blogEntry);
    public BlogEntryList findAllBlogEntries(Long blogId);
}
