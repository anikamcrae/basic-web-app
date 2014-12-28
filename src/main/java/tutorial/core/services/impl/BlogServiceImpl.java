package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.Blog;
import tutorial.core.models.entities.BlogEntry;
import tutorial.core.repositories.BlogEntryRepo;
import tutorial.core.repositories.BlogRepo;
import tutorial.core.services.BlogService;
import tutorial.core.services.exceptions.BlogNotFoundException;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.BlogList;

/**
 * Created by Anika McRae on 27/12/2014.
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private BlogEntryRepo blogEntryRepo;



    @Override
    public Blog findBlog(Long blogId) {
        return blogRepo.findBlog(blogId);
    }



    @Override
    public BlogList findAllBlogs() {
        return new BlogList(blogRepo.findAllBlogs());
    }



    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry blogEntry) {
        Blog blog = blogRepo.findBlog(blogId);

        if (blog == null) {
            throw new BlogNotFoundException();
        }

        BlogEntry newBlogEntry = blogEntryRepo.createBlogEntry(blogEntry);
        newBlogEntry.setBlog(blog);

        return newBlogEntry;
    }



    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        if (blogRepo.findBlog(blogId) == null) {
            throw new BlogNotFoundException();
        }

        return new BlogEntryList(blogId, blogEntryRepo.findByBlogId(blogId));
    }
}
