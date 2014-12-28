package tutorial.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Anika McRae on 17/12/2014.
 */
@Entity
public class BlogEntry {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String content;

    @ManyToOne //many blog entries to one blog
    private Blog blog;

    public Blog getBlog() {
        return blog;
    }
    public void setBlog(Blog blog) { this.blog = blog; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }


}
