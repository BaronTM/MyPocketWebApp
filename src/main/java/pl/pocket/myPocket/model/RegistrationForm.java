package pl.pocket.myPocket.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationForm {

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String passwordConfirmation;

}
