package pl.pocket.myPocket.controller.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @Getter
    @Setter
    private String userName;

    @Getter @Setter
    private String password;
}
