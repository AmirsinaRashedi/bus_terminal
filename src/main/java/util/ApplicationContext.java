package util;

import repository.Impl.BusRepositoryImpl;
import repository.Impl.PassengerRepositoryImpl;
import repository.Impl.TicketRepositoryImpl;
import service.BusService;
import service.PassengerService;
import service.TicketService;
import service.impl.BusServiceImpl;
import service.impl.PassengerServiceImpl;
import service.impl.TicketServiceImpl;

public class ApplicationContext {

    private static BusService busService;

    private static PassengerService passengerService;

    private static TicketService ticketService;

    private ApplicationContext() {

    }

    public static BusService getBusService() {

        if (busService == null)
            busService = new BusServiceImpl(new BusRepositoryImpl());

        return busService;

    }

    public static PassengerService getPassengerService() {

        if (passengerService == null)
            passengerService = new PassengerServiceImpl(new PassengerRepositoryImpl());

        return passengerService;

    }

    public static TicketService getTicketService() {

        if (ticketService == null)
            ticketService = new TicketServiceImpl(new TicketRepositoryImpl());

        return ticketService;

    }

}
