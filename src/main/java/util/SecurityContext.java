package util;

import domain.Passenger;

public class SecurityContext {

    private static Passenger passenger;

    private SecurityContext() {
    }

    public static Passenger getPassenger() {

        return passenger;

    }

    public static void setPassenger(Passenger passenger) {

        SecurityContext.passenger = passenger;

    }

    public static void logout() {

        passenger = null;

    }

}
