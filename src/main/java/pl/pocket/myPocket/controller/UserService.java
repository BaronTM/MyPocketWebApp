package pl.pocket.myPocket.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import pl.pocket.myPocket.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User getUserFromRepository(String userName) {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String select = "from User where userName=:un";
        Query query = session.createQuery(select);
        query.setParameter("un", userName);
        List resultList = query.getResultList();
        User user = null;
        if (resultList.size() > 0) {
            user = (User) resultList.get(0);
        }
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Transactional
    public void persistUser(User user) {
        entityManager.persist(user);
    }


    private SessionFactory getSessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(Expense.class);
        configuration.addAnnotatedClass(ExpenseCategory.class);
        configuration.addAnnotatedClass(Revenue.class);
        configuration.addAnnotatedClass(RevenueCategory.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Wallet.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }



}
