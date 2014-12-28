package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.Blog;
import tutorial.core.repositories.BlogRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Anika McRae on 27/12/2014.
 */
@Repository
public class JpaBlogRepo implements BlogRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Blog createBlog(Blog blog) {
        em.persist(blog);
        return blog;
    }



    @Override
    public List<Blog> findAllBlogs() {
        Query query = em.createQuery("Select b from Blog b");
        return query.getResultList();
    }



    @Override
    public Blog findBlog(Long id) {
        return em.find(Blog.class, id);
    }



    @Override
    public Blog findBlogByTitle(String title) {
        Query query = em.createQuery("Select b from Blog b where b.title = :title");
        query.setParameter("title", title);

        List<Blog> blogs = query.getResultList();

        if (blogs.isEmpty()) {
            return null;
        }

        return blogs.get(0);

    }



    @Override
    public List<Blog> findBlogsByAccount(Long accountId) {
        Query query = em.createQuery("Select b from Blog b where b.owner.id = :accountId");
        query.setParameter("accountId", accountId);

        return query.getResultList();
    }


}
