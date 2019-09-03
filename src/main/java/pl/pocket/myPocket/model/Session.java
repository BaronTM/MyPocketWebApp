package pl.pocket.myPocket.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Session {

    @Getter
    @Setter
    private User user;

}
