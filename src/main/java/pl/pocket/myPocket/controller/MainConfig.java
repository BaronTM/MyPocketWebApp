package pl.pocket.myPocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pocket.myPocket.controller.repository.UserRepository;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = "pl.pocket.myPocket")
@EntityScan(basePackages = "pl.pocket.myPocket")
public class MainConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder noopEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @PostConstruct
    public void init(){
//        User user = new User("ernest", "1234");
//        Wallet wallet = user.getWallet();
//
//        user.getRoles().add(new Role(user, "ADMIN"));
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
//        userRepository.persistUser(user);
    }

}
