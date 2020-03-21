package pl.pocket.myPocket.controller.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pocket.myPocket.controller.repository.UserRepository;
import pl.pocket.myPocket.controller.services.PocketUserDetailService;
import pl.pocket.myPocket.controller.utils.JsonResponse;
import pl.pocket.myPocket.controller.utils.JwtUtil;

@RestController
@CrossOrigin
public class App {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PocketUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JsonResponse jsonResponse;

    @RequestMapping("/app")
    public String app(Authentication authentication) {
        return jsonResponse.getJsonResponseWithToken(authentication.getName(), authentication.getName());
    }

}
