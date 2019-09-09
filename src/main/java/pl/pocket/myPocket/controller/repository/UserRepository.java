package pl.pocket.myPocket.controller.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.pocket.myPocket.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User getUserFromRepository(String userName) {
        Query query = entityManager.createQuery("FROM User WHERE userName=:un");
        query.setParameter("un", userName);
        User user = (User) query.getSingleResult();
        return user;
    }

    @Transactional
    public void persistUser(User user) throws Exception {
        entityManager.persist(user);
    }

    @Transactional
    public boolean checkIfUserExists(String userName) {
        Query query = entityManager.createQuery("FROM User WHERE userName=:un");
        query.setParameter("un", userName);
        return query.getResultList().size() >= 1;
    }

}
