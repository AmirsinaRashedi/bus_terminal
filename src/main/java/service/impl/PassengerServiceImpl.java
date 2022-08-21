package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.Passenger;
import repository.PassengerRepository;
import service.PassengerService;

import java.util.Scanner;

public class PassengerServiceImpl extends BaseServiceImpl<Passenger, Long, PassengerRepository>
        implements PassengerService {

    public PassengerServiceImpl(PassengerRepository repository) {
        super(repository);
    }

    @Override
    public Passenger findByUsername(String username) {

        if (username != null && !username.isBlank()) {

            try {

                return repository.findByUsername(username);

            } catch (Exception e) {

                return null;

            }

        } else
            return null;

    }

    @Override
    public boolean createPassenger() {

        Scanner stringInput = new Scanner(System.in);

        System.out.print("enter username: ");

        String newUsername = stringInput.nextLine();

        if (findByUsername(newUsername) == null) {

            try {

                Passenger newPassenger = new Passenger();

                newPassenger.setUsername(newUsername);

                System.out.print("enter password: ");

                newPassenger.setPassword(stringInput.nextLine());

                System.out.print("enter firstname: ");

                newPassenger.setFirstname(stringInput.nextLine());

                System.out.print("enter lastname: ");

                newPassenger.setLastname(stringInput.nextLine());

                save(newPassenger);

            } catch (Exception e) {

                System.out.println("new Passenger not created!");

                return false;

            }

            return true;

        } else {

            System.out.println("this username is taken!");

            return false;

        }

    }

    @Override
    public boolean createPassenger(String newUsername, String newPassword, String newFirstname, String newLastname) {

        if (newUsername == null || newPassword == null || newUsername.isBlank() || newPassword.isBlank() || newFirstname == null || newLastname == null || newFirstname.isBlank() || newLastname.isBlank())
            return false;


        if (findByUsername(newUsername) == null) {

            try {

                Passenger newPassenger = new Passenger();

                newPassenger.setUsername(newUsername);

                newPassenger.setPassword(newPassword);

                newPassenger.setFirstname(newFirstname);

                newPassenger.setLastname(newLastname);

                save(newPassenger);

            } catch (Exception e) {

                System.out.println("new Passenger not created!");

                return false;

            }

            return true;

        } else {

            System.out.println("this username is taken!");

            return false;

        }
    }
}
