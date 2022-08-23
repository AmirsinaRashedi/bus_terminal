package domain;

import base.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Where;

@Entity
@Table(name = Ticket.TABLE_NAME)
@Where(clause = "is_deleted = false")
public class Ticket extends BaseEntity<Long> {

    public static final String TABLE_NAME = "ticket";

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Bus bus;

    private char gender;

    public Ticket() {
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {

        return passenger.toString()
                + "   "
                + bus.toString();

    }
}
