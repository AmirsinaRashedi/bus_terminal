package repository;

import base.repository.BaseRepository;
import domain.Passenger;
import domain.Ticket;

import java.util.List;

public interface TicketRepository extends BaseRepository<Ticket, Long> {

    List<Ticket> getByPassenger(Passenger passenger);
}
