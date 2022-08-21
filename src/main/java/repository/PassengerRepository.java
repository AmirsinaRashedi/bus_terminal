package repository;

import base.repository.BaseRepository;
import domain.Passenger;

public interface PassengerRepository extends BaseRepository<Passenger, Long> {

    Passenger findByUsername(String username);


}
