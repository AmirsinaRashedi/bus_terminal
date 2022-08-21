package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Passenger;
import repository.PassengerRepository;
import util.HibernateUtil;

public class PassengerRepositoryImpl extends BaseRepositoryImpl<Passenger, Long>
        implements PassengerRepository {


    public PassengerRepositoryImpl() {
        super(HibernateUtil.getEntityManager());
    }

    @Override
    public Class<Passenger> getClassType() {
        return Passenger.class;
    }

    @Override
    public Passenger findByUsername(String username) {
        return em.createQuery("select p from Passenger p where p.username = :username", Passenger.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
