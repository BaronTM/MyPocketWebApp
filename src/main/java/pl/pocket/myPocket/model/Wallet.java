package pl.pocket.myPocket.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wallet")
public class Wallet {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wallet")
    private Integer idWallet;

    @Getter
    @Setter
    @OneToOne(mappedBy = "wallet", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    @Getter
    @Setter
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Account> accountList;

    @Getter
    @Setter
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<ExpenseCategory> expenceList;

    @Getter
    @Setter
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<RevenueCategory> revenueList;

    public Wallet(User user) {
        this.user = user;
        this.accountList = new ArrayList<>();
        this.expenceList = new ArrayList<>();
        this.revenueList = new ArrayList<>();
    }

    public void addAccount(String accountName) {
        Account account = new Account(accountName, this);
        accountList.add(account);
    }

    public void addExpenseCategory(String categoryName) {
        ExpenseCategory expenseCategory = new ExpenseCategory(categoryName, this);
        expenceList.add(expenseCategory);
    }

    public void addRevenueCategory(String categoryName) {
        RevenueCategory revenueCategory = new RevenueCategory(categoryName, this);
        revenueList.add(revenueCategory);
    }
}
