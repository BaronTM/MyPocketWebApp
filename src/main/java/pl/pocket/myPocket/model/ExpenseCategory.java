package pl.pocket.myPocket.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "expense_category")
public class ExpenseCategory {

    @Id
    @Column(name = "id_expense_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer idExpenseCategory;

    @Getter
    @Setter
    @Column(name = "expense_category_name")
    private String categoryName;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    public ExpenseCategory(String categoryName, Wallet wallet) {
        this.categoryName = categoryName;
        this.wallet = wallet;
    }

}
