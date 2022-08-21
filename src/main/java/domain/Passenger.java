package domain;

import base.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = Passenger.TABLE_NAME)
public class Passenger extends BaseEntity<Long> {

    public static final String TABLE_NAME = "passenger";

    private String firstname;

    private String lastname;

    private String username;

    private String password;


    public Passenger() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {

        return "firstname: " + firstname
                + " lastname: " + lastname
                + " username: " + username;

    }

}
