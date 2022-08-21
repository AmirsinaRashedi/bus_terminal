package domain;

import base.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = Ticket.TABLE_NAME)
public class Ticket extends BaseEntity<Long> {

    public static final String TABLE_NAME = "ticket";

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Bus bus;

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

    @Override
    public String toString() {

        return passenger.toString()
                + "   "
                + bus.toString();

    }
}
