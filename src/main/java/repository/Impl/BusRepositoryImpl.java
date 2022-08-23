package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Bus;
import repository.BusRepository;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class BusRepositoryImpl extends BaseRepositoryImpl<Bus, Long>
        implements BusRepository {


    public BusRepositoryImpl() {
        super(HibernateUtil.getEntityManager());
    }

    @Override
    public Class<Bus> getClassType() {
        return Bus.class;
    }

    @Override
    public List<Bus> findWithOriginAndDestination(String origin, String destination) {
        return em.createQuery("select b from Bus b where b.origin = :origin and b.destination = :destination", Bus.class)
                .setParameter("origin", origin)
                .setParameter("destination", destination)
                .getResultList();
    }

    @Override
    public List<Bus> findWithOriginAndDestinationAndDate(String origin, String destination, LocalDate departureDate) {
        return em.createQuery("select b from Bus b where b.origin = :origin and b.destination = :destination and b.departureDate = :date order by b.departureTime ASC ", Bus.class)
                .setParameter("origin", origin)
                .setParameter("destination", destination)
                .setParameter("date", departureDate)
                .getResultList();
    }

    @Override
    public List<Bus> groupAllByOrigin(String order) {
        return em.createQuery("select b from Bus b order by b.origin ".concat(order), Bus.class)
                .getResultList();
    }

    @Override
    public List<Bus> groupAllByDestination(String order) {
        return em.createQuery("select b from Bus b order by b.destination ".concat(order), Bus.class)
                .getResultList();
    }

    @Override
    public List<Bus> groupAllByPrice(String order) {
        return em.createQuery("select b from Bus b order by b.price ".concat(order), Bus.class)
                .getResultList();
    }

    @Override
    public List<String> getAllOrigins() {
        return em.createQuery("select b.origin from Bus b", String.class)
                .getResultList();
    }

    @Override
    public List<String> getAllDestinations() {
        return em.createQuery("select b.destination from Bus b", String.class)
                .getResultList();
    }

}
