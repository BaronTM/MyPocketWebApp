package pl.pocket.myPocket.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import pl.pocket.myPocket.model.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "pl.pocket.myPocket")
public class MainConfig {

    @PostConstruct
    public void init(){
//        User user = new User("Adam", "Kowalski");
//        Wallet wallet = user.getWallet();
//
//        wallet.addAccount("pko");
//        wallet.addAccount("nest");
//
//        wallet.addExpenseCategory("home");
//        wallet.addExpenseCategory("car");
//        wallet.addExpenseCategory("food and drink");
//
//        wallet.addRevenueCategory("job");
//        wallet.addRevenueCategory("business");
//
//        Account account1 = wallet.getAccountList().get(0);
//        Account account2 = wallet.getAccountList().get(1);
//
//        Revenue revenue1 = new Revenue(wallet.getRevenueList().get(0), account1, 5000);
//        Revenue revenue2 = new Revenue(wallet.getRevenueList().get(1), account1, 3000);
//
//        account1.addRevenue(revenue1);
//        account1.addRevenue(revenue2);
//
//        Expense expense1 = new Expense(wallet.getExpenceList().get(0), account1, 999);
//        Expense expense2 = new Expense(wallet.getExpenceList().get(0), account1, 545);
//        Expense expense3 = new Expense(wallet.getExpenceList().get(1), account1, 600);
//        Expense expense4 = new Expense(wallet.getExpenceList().get(2), account1, 1000);
//
//        account1.addExpense(expense1);
//        account1.addExpense(expense2);
//        account1.addExpense(expense3);
//        account1.addExpense(expense4);
//
//        System.out.println("Revenues: " + wallet.getAccountList().get(0).getAllRevenuesValue());
//        System.out.println("Expences: " + wallet.getAccountList().get(0).getAllExpensesValue());
//        System.out.println("Blance: " + wallet.getAccountList().get(0).getBallance());
//
//        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
//        configuration.configure("hibernate.cfg.xml");
//        configuration.addAnnotatedClass(Account.class);
//        configuration.addAnnotatedClass(Expense.class);
//        configuration.addAnnotatedClass(ExpenseCategory.class);
//        configuration.addAnnotatedClass(Revenue.class);
//        configuration.addAnnotatedClass(RevenueCategory.class);
//        configuration.addAnnotatedClass(User.class);
//        configuration.addAnnotatedClass(Wallet.class);
//        SessionFactory sessionFactory = configuration.buildSessionFactory();
//        org.hibernate.Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//
//        session.persist(user);
//
//        session.getTransaction().commit();
//        sessionFactory.close();

    }

}
