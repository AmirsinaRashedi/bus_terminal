package service;

import base.service.BaseService;
import domain.Bus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BusService extends BaseService<Bus, Long> {

    List<Bus> findWithOriginAndDestination(String origin, String destination);

    List<Bus> findWithOriginAndDestinationAndDate(String origin, String destination, LocalDate departureDate);

    List<Bus> groupAllByOrigin(String order);

    List<Bus> groupAllByDestination(String order);

    List<Bus> groupAllByPrice(String order);

    Boolean createBus();

    Boolean createBus(String origin, String destination, Integer price, Integer availableSeats, LocalDate departureDate, LocalTime departureTime);

    List<String> getAllOrigins();

    List<String> getAllDestinations();


}
