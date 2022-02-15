package pl.edu.wszib.kotarba.garbage.management.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.kotarba.garbage.management.database.IBagDAO;
import pl.edu.wszib.kotarba.garbage.management.model.Bag;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class BagDAOImpl implements IBagDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Bag> getBags() {
        Session session = this.sessionFactory.openSession();
        Query<Bag> query = session.createQuery("FROM pl.edu.wszib.kotarba.garbage.management.model.Bag");
        List<Bag> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Bag> getBagById(int bagId) {
        Session session = this.sessionFactory.openSession();
        Query<Bag> query = session.createQuery("FROM pl.edu.wszib.kotarba.garbage.management.model.Bag WHERE id = :id");
        query.setParameter("id", bagId);
        try {
            Bag bag = query.getSingleResult();
            session.close();
            return Optional.of(bag);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Bag> getBagByColor(String color) {
        Session session = this.sessionFactory.openSession();
        Query<Bag> query = session.createQuery("FROM pl.edu.wszib.kotarba.garbage.management.model.Bag WHERE color = :color");
        query.setParameter("color", color);
        try {
            Bag bag = query.getSingleResult();
            session.close();
            return Optional.of(bag);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateBag(Bag bag) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(bag);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
