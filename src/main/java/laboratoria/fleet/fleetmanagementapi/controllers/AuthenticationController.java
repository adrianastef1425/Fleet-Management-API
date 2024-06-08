package laboratoria.fleet.fleetmanagementapi.controllers;

import laboratoria.fleet.fleetmanagementapi.dto.LoginResponse;
import laboratoria.fleet.fleetmanagementapi.dto.LoginUserDto;
import laboratoria.fleet.fleetmanagementapi.dto.LoginUserResponse;
import laboratoria.fleet.fleetmanagementapi.dto.RegisterUserDto;
import laboratoria.fleet.fleetmanagementapi.entities.User;
import laboratoria.fleet.fleetmanagementapi.services.AuthenticationService;
import laboratoria.fleet.fleetmanagementapi.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginUserResponse loginUserResponse = new LoginUserResponse(authenticatedUser.getId(), authenticatedUser.getEmail());
        LoginResponse loginResponse = new LoginResponse(
                jwtToken,
                jwtService.getExpirationTime(),
                loginUserResponse
        );
        //loginResponse.setToken(jwtToken);
        //loginResponse.setExpiresIn(jwtService.getExpirationTime());
        //LoginUserResponse loginUserResponse = new LoginUserResponse(authenticatedUser.getId(), authenticatedUser.getEmail());
        //loginUserResponse.setId();
        //loginUserResponse.setEmail();
        //loginResponse.setUser(loginUserResponse);
        return ResponseEntity.ok(loginResponse);
    }


}