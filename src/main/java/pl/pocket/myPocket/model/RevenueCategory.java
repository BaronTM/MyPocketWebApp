package pl.pocket.myPocket.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "revenue_category")
public class RevenueCategory {

    @Id
    @Column(name = "id_revenue_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer idRevenueCategoty;

    @Getter
    @Setter
    @Column(name = "revenue_category_name")
    private String categoryName;

    @Getter
    @Setter
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    public RevenueCategory(String categoryName, Wallet wallet) {
        this.categoryName = categoryName;
        this.wallet = wallet;
    }

}
