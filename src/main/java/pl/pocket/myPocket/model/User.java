package pl.pocket.myPocket.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Getter
    @Setter
    @Column(name = "user_name")
    private String userName;

    @Getter
    @Setter
    @Column(name = "user_password")
    private String password;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.wallet = new Wallet(this);
    }
}
