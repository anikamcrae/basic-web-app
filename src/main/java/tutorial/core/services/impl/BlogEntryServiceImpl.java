package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.BlogEntry;
import tutorial.core.repositories.BlogEntryRepo;
import tutorial.core.services.BlogEntryService;

/**
 * Created by Anika McRae on 27/12/2014.
 */
@Service @Transactional
public class BlogEntryServiceImpl implements BlogEntryService {

    @Autowired
    private BlogEntryRepo repo;

    @Override
    public BlogEntry findBlogEntry(Long blogEntryId) {
        return null;
    }



    @Override
    public BlogEntry deleteBlogEntry(Long blogEntryId) {
        return null;
    }



    @Override
    public BlogEntry updateBlogEntry(Long blogEntryId, BlogEntry blogEntry) {
        return null;
    }
}
