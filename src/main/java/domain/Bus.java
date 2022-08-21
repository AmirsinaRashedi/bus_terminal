package domain;

import base.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = Bus.TABLE_NAME)
@Where(clause = "availableSeats > 0")
public class Bus extends BaseEntity<Long> {

    public static final String TABLE_NAME = "bus";

    private String origin;

    private String destination;

    private LocalDate departureDate;

    private LocalTime departureTime;

    private int availableSeats;

    private int price;

    public Bus() {
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void fillOneSeat() {
        availableSeats--;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {

        return "Origin: " + origin
                + " Destination: " + destination
                + " Date: " + departureDate
                + " Time: " + departureTime
                + " price: " + price
                + " Available seats: " + availableSeats;

    }

}
