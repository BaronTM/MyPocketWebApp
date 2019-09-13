package pl.pocket.myPocket.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Role> roles;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_wallet")
    private Wallet wallet;

    @Getter
    @Setter
    @Column(name = "enabled")
    private boolean enabled;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.wallet = new Wallet(this);
        this.roles = new ArrayList<>();
        this.enabled = true;
        roles.add(new Role(this, "USER"));
    }
}
