package eliandra.juliano.travelia.controllers;

import eliandra.juliano.travelia.dtos.LoginRequestDto;
import eliandra.juliano.travelia.dtos.LoginResponseDto;
import eliandra.juliano.travelia.dtos.RegisterDto;
import eliandra.juliano.travelia.dtos.UserDto;
import eliandra.juliano.travelia.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@RequestBody LoginRequestDto authenticationDto)  { // @Valid
        return authenticationService.login(authenticationDto);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody RegisterDto registerDto) {
        return authenticationService.register(registerDto);
    }
}
