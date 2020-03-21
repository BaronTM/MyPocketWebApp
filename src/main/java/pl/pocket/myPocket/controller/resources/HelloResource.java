package pl.pocket.myPocket.controller.resources;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.pocket.myPocket.controller.authentication.AuthenticationRequest;
import pl.pocket.myPocket.controller.authentication.AuthenticationResponse;
import pl.pocket.myPocket.controller.services.PocketUserDetailService;
import pl.pocket.myPocket.controller.utils.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class HelloResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PocketUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private Gson gson;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello world";
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

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "jwt=" + jwt + "; Max-Age=1800;");

        ResponseEntity responseEntity = new ResponseEntity(gson.toJson(jwt), headers, HttpStatus.OK);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
