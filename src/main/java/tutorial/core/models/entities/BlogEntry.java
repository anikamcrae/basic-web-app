package tutorial.core.models.entities;

/**
 * Created by Anika McRae on 17/12/2014.
 */
public class BlogEntry {

    private Long id;
    private String title;
    private Blog blog;

    public Blog getBlog() {
        return blog;
    }
    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


}
