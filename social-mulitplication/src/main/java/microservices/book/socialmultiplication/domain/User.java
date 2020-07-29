package microservices.book.socialmultiplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public final class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;
    private final String alias;

    public User(String alias) {
        this.alias = alias;
    }

    protected User (){
        alias = null;
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
            Objects.equals(alias, user.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alias);
    }
}
