package pl.edu.wszib.kotarba.garbage.management.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.kotarba.garbage.management.database.ITruckDAO;
import pl.edu.wszib.kotarba.garbage.management.model.Truck;

import java.util.List;

@Repository
public class TruckDAOImpl implements ITruckDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addTruck(Truck truck) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(truck);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Truck> getTrucksByUserId(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Truck> query = session.createQuery("FROM pl.edu.wszib.kotarba.garbage.management.model.Truck WHERE user_id = :userId");
        query.setParameter("userId", userId);
        List<Truck> result = query.getResultList();
        session.close();
        return result;
    }
}
