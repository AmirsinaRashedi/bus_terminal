package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.Bus;
import domain.Passenger;
import domain.Ticket;
import repository.TicketRepository;
import service.TicketService;
import util.ApplicationContext;

import java.util.List;
import java.util.Scanner;

public class TicketServiceImpl extends BaseServiceImpl<Ticket, Long, TicketRepository>
        implements TicketService {

    public TicketServiceImpl(TicketRepository repository) {
        super(repository);
    }

    @Override
    public List<Ticket> getByPassenger(Passenger passenger) {

        if (passenger != null) {

            try {

                return repository.getByPassenger(passenger);

            } catch (Exception e) {

                return null;

            }

        } else
            return null;

    }

    @Override
    public boolean buyTicket(Passenger passenger) {

        Scanner stringInput = new Scanner(System.in);

        Scanner intInput = new Scanner(System.in);

        String origin, destination;

        System.out.print("enter Origin: ");

        origin = stringInput.nextLine();

        System.out.print("enter Destination: ");

        destination = stringInput.nextLine();

        List<Bus> withOriginAndDestination = ApplicationContext.getBusService().findWithOriginAndDestination(origin, destination);

        int busCount = 0;

        if (!withOriginAndDestination.isEmpty()) {

            for (Bus b : withOriginAndDestination)
                System.out.println(++busCount + "- " + b.toString());

        } else {

            System.out.println("no buses with this specification exists");

            return false;

        }

        System.out.print("choose: ");

        int choice = intInput.nextInt();

        if (choice > 0 && choice <= busCount) {

            Bus boughtBus = withOriginAndDestination.get(choice - 1);

            Ticket newTicket = new Ticket();

            newTicket.setPassenger(passenger);

            newTicket.setBus(boughtBus);

            save(newTicket);

            boughtBus.fillOneSeat();

            ApplicationContext.getBusService().save(boughtBus);

            return true;

        } else {

            System.out.println("wrong input!");

            return false;

        }

    }

    @Override
    public boolean buyTicket(Passenger passenger, Bus boughtBus, char gender) {

        if (boughtBus == null || passenger == null)
            return false;

        Ticket newTicket = new Ticket();

        newTicket.setPassenger(passenger);

        newTicket.setBus(boughtBus);

        newTicket.setGender(gender);

        save(newTicket);

        if (ApplicationContext.getBusService().save(boughtBus) == null)
            return false;

        boughtBus.fillOneSeat();

        return true;

    }


}
