package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.BlogEntry;
import tutorial.core.repositories.BlogEntryRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Anika McRae on 27/12/2014.
 */
@Repository
public class JpaBlogEntryRepo implements BlogEntryRepo {

    @PersistenceContext
    private EntityManager em;



    @Override
    public BlogEntry createBlogEntry(BlogEntry data) {
        em.persist(data);
        return data;
    }



    @Override
    public BlogEntry deleteBlogentry(Long id) {
        BlogEntry blogEntry = em.find(BlogEntry.class, id);
        em.remove(blogEntry);

        return blogEntry;
    }




    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
        BlogEntry entry = em.find(BlogEntry.class, id);
        entry.setTitle(data.getTitle());
        entry.setContent(data.getContent());

        return entry;
    }



    @Override
    public BlogEntry findBlogEntry(Long id) {
        return em.find(BlogEntry.class, id);
    }



    @Override
    public List<BlogEntry> findByBlogId(Long blogId) {
        Query query = em.createQuery("select be from BlogEntry be where be.blog.id = :blogId");
        query.setParameter("blogId", blogId);
        return query.getResultList();
    }
}
