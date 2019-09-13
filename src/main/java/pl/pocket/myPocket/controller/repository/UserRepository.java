package pl.pocket.myPocket.controller.repository;

import org.springframework.stereotype.Repository;
import pl.pocket.myPocket.model.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User getUserFromRepository(String userName) {
        Query query = entityManager.createQuery("FROM User WHERE userName=:un");
        query.setParameter("un", userName);
        query.
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
