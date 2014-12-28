package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.Blog;
import tutorial.core.models.entities.BlogEntry;
import tutorial.core.repositories.BlogEntryRepo;
import tutorial.core.repositories.BlogRepo;
import tutorial.core.services.BlogService;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.BlogList;

/**
 * Created by Anika McRae on 27/12/2014.
 */
@Service @Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private BlogEntryRepo blogEntryRepo;

    @Override
    public Blog findBlog(Long blogId) {
        return null;
    }



    @Override
    public BlogList findAllBlogs() {
        return null;
    }



    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry blogEntry) {
        return null;
    }



    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        return null;
    }
}
