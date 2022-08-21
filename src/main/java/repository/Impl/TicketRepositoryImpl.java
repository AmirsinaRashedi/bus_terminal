package repository.Impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Passenger;
import domain.Ticket;
import repository.TicketRepository;
import util.HibernateUtil;

import java.util.List;

public class TicketRepositoryImpl extends BaseRepositoryImpl<Ticket, Long>
        implements TicketRepository {

    public TicketRepositoryImpl() {
        super(HibernateUtil.getEntityManager());
    }

    @Override
    public Class<Ticket> getClassType() {
        return Ticket.class;
    }

    @Override
    public List<Ticket> getByPassenger(Passenger passenger) {
        return em.createQuery("select t from Ticket t where t.passenger = :passenger", Ticket.class)
                .setParameter("passenger", passenger)
                .getResultList();
    }
}
