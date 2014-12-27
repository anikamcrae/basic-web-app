package tutorial.core.services;

import tutorial.core.models.entities.BlogEntry;

/**
 * Created by Anika McRae on 18/12/2014.
 */
public interface BlogEntryService {

    public BlogEntry findBlogEntry(Long blogEntryId);
    public BlogEntry deleteBlogEntry(Long blogEntryId);
    public BlogEntry updateBlogEntry(Long blogEntryId, BlogEntry blogEntry);

}
