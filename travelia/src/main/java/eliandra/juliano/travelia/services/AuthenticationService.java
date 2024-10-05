package eliandra.juliano.travelia.services;

import eliandra.juliano.travelia.dtos.LoginRequestDto;
import eliandra.juliano.travelia.dtos.LoginResponseDto;
import eliandra.juliano.travelia.dtos.RegisterDto;
import eliandra.juliano.travelia.dtos.UserDto;
import eliandra.juliano.travelia.models.Usuario;
import eliandra.juliano.travelia.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


public class AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TokenService tokenService;
    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto)
            throws AuthenticationException {
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(
                loginRequestDto.usuario(), loginRequestDto.senha()
        );
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        Usuario usuario = (Usuario) auth.getPrincipal();
        String token = tokenService.generateToken(usuario);
        return new LoginResponseDto(token);
    }
    @Transactional
    public UserDto register(RegisterDto registerDto) {
        Usuario usuario = new Usuario();
        usuario.setUsuario(registerDto.usuario());
        usuario.setEmail(registerDto.email());
        usuario.setSenha(new BCryptPasswordEncoder().encode(registerDto.senha()));
        Usuario savedUser = usuarioRepository.save(usuario);
        return new UserDto(savedUser.getUsuario(), savedUser.getEmail());
    }

}
