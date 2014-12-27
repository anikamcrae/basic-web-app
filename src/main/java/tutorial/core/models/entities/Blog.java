package tutorial.core.models.entities;

/**
 * Created by Anika McRae on 19/12/2014.
 */
public class Blog {

    private Long id;
    private String title;
    private Account owner;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Account getOwner() {
        return owner;
    }
    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }




}
