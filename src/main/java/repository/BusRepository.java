package repository;

import base.repository.BaseRepository;
import domain.Bus;

import java.time.LocalDate;
import java.util.List;

public interface BusRepository extends BaseRepository<Bus, Long> {

    List<Bus> findWithOriginAndDestination(String origin, String destination);

    List<Bus> findWithOriginAndDestinationAndDate(String origin, String destination, LocalDate departureDate);

    List<Bus> groupAllByOrigin(String order);

    List<Bus> groupAllByDestination(String order);

    List<Bus> groupAllByPrice(String order);

    List<String> getAllOrigins();

    List<String> getAllDestinations();

}
