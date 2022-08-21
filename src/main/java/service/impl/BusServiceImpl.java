package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.Bus;
import repository.BusRepository;
import service.BusService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BusServiceImpl extends BaseServiceImpl<Bus, Long, BusRepository>
        implements BusService {


    public BusServiceImpl(BusRepository repository) {
        super(repository);
    }

    @Override
    public List<Bus> findWithOriginAndDestination(String origin, String destination) {

        if (origin != null && destination != null && !origin.isBlank() && !destination.isBlank()) {

            try {

                return repository.findWithOriginAndDestination(origin, destination);

            } catch (Exception e) {

                return null;

            }

        } else
            return null;

    }

    @Override
    public List<Bus> findWithOriginAndDestinationAndDate(String origin, String destination, LocalDate departureDate) {
        if (origin != null && destination != null && !origin.isBlank() && !destination.isBlank() && departureDate != null) {

            try {

                return repository.findWithOriginAndDestinationAndDate(origin, destination, departureDate);

            } catch (Exception e) {

                return null;

            }

        } else
            return null;
    }

    @Override
    public List<Bus> groupAllByOrigin(String order) {

        if (order != null && (order.equals("ASC") || order.equals("DESC"))) {

            try {

                return repository.groupAllByOrigin(order);

            } catch (Exception e) {

                return null;

            }

        } else
            return null;

    }

    @Override
    public List<Bus> groupAllByDestination(String order) {

        if (order != null && (order.equals("ASC") || order.equals("DESC"))) {

            try {

                return repository.groupAllByDestination(order);

            } catch (Exception e) {

                return null;

            }

        } else
            return null;

    }

    @Override
    public List<Bus> groupAllByPrice(String order) {

        if (order != null && (order.equals("ASC") || order.equals("DESC"))) {

            try {

                return repository.groupAllByPrice(order);

            } catch (Exception e) {

                return null;

            }

        } else
            return null;

    }

    @Override
    public Boolean createBus() {

        Bus newBus = new Bus();

        Scanner stringInput = new Scanner(System.in);

        Scanner intInput = new Scanner(System.in);

        try {


            System.out.print("enter origin: ");

            newBus.setOrigin(stringInput.nextLine());

            System.out.print("enter destination: ");

            newBus.setDestination(stringInput.nextLine());

            System.out.print("enter price: ");

            newBus.setPrice(intInput.nextInt());

            System.out.print("enter number of available seats: ");

            newBus.setAvailableSeats(intInput.nextInt());

            save(newBus);

            return true;

        } catch (Exception e) {

            System.out.println("new Bus not created!");

            return false;

        }


    }

    @Override
    public Boolean createBus(String origin, String destination, Integer price, Integer availableSeats, LocalDate departureDate, LocalTime departureTime) {

        Bus newBus = new Bus();

        try {

            newBus.setOrigin(origin);

            newBus.setDestination(destination);

            newBus.setPrice(price);

            newBus.setAvailableSeats(availableSeats);

            newBus.setDepartureDate(departureDate);

            newBus.setDepartureTime(departureTime);

            return save(newBus) != null;

        } catch (Exception e) {

            System.out.println("new Bus not created!");

            return false;

        }

    }

    @Override
    public List<String> getAllOrigins() {

        List<String> originsWithDuplicates;

        try {

            originsWithDuplicates = repository.getAllOrigins();

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }


        return originsWithDuplicates.stream().distinct().collect(Collectors.toList());

    }

    @Override
    public List<String> getAllDestinations() {

        List<String> destinationsWithDuplicates;

        try {

            destinationsWithDuplicates = repository.getAllDestinations();

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

        return destinationsWithDuplicates.stream().distinct().collect(Collectors.toList());

    }

}
