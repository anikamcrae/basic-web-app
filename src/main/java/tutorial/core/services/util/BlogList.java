package tutorial.core.services.util;

import tutorial.core.models.entities.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public class BlogList {

    private List<Blog> blogs = new ArrayList<Blog>();

    public BlogList(List<Blog> blogs) {
        this.blogs = blogs;
    }


    public List<Blog> getBlogs() { return blogs; }
    public void setBlogs(List<Blog> blogs) { this.blogs = blogs; }
}
