package service;

import base.service.BaseService;
import domain.Passenger;

public interface PassengerService extends BaseService<Passenger, Long> {

    Passenger findByUsername(String username);

    boolean createPassenger();

    boolean createPassenger(String newUsername, String newPassword, String newFirstname, String newLastname);

}
