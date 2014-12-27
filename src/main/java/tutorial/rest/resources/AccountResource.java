package tutorial.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import tutorial.core.models.entities.Account;

/**
 * Created by Anika McRae on 23/12/2014.
 *
 * Converts Resource to Entity
 */
public class AccountResource extends ResourceSupport {

    private String name;
    private String password;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @JsonIgnore public String getPassword() { return password; }
    @JsonProperty public void setPassword(String password) { this.password = password; }



    public Account toAccount() {
        Account account = new Account();
        account.setName(name);
        account.setPassword(password);

        return account;
    }



}
