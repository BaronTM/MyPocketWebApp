package pl.pocket.myPocket.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.pocket.myPocket.controller.repository.UserRepository;
import pl.pocket.myPocket.controller.services.PocketUserDetailService;
import pl.pocket.myPocket.controller.utils.JwtUtil;
import pl.pocket.myPocket.model.entities.User;

import java.util.*;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
public class AuthenticationController {

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


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest registrationRequest) throws Exception {
        Pattern loginPatern = Pattern.compile("^[a-zA-Z0-9.+_-]{5,50}$");
        Pattern emailPatern = Pattern.compile("^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.+_-]{5,50}$");
        Pattern passwordPatern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$");

        final String userName = registrationRequest.getUserName();
        final String password = registrationRequest.getPassword();

        Map<String, String> resultMap = new HashMap<String, String>();

        if (!((loginPatern.matcher(userName).matches() || emailPatern.matcher(userName).matches())
                && passwordPatern.matcher(password).matches())) {
            resultMap.put("result", "INCORRECT");
        } else if (userRepository.checkIfUserExists(userName)) {
            resultMap.put("result", "OCCUPIED");
        } else {
            User newUser = new User(userName, passwordEncoder.encode(password));
            userRepository.persistUser(newUser);
            resultMap.put("result", "OK");
        }

        return ResponseEntity.ok(resultMap);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUserName());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

