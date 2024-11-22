package com.example.nexosapp.seguridad.auth;
import com.example.nexosapp.DTO.CivilCrearDTO;
import com.example.nexosapp.DTO.UsuarioLogDTO;
import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.modelos.Token;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.seguridad.JWTservice;
import com.example.nexosapp.servicios.CivilServicio;
import com.example.nexosapp.servicios.TokenServicio;
import com.example.nexosapp.servicios.UsuarioService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private UsuarioService usuarioService;
    private JWTservice jwTservice;
    private TokenServicio tokenServicio;
    private CivilServicio civilServicio;

    @PostMapping("/login")
    public AuthDTO login(@RequestBody UsuarioLogDTO usuarioDTO) {
        Usuario usuario = usuarioService.buscarPorUsername(usuarioDTO.getUsuario());
        String apiKey = null;
        String mensaje;
        Boolean vereificado = false;

        if (usuario != null) {
            if (usuarioService.validarContrasenya(usuario, usuarioDTO.getContrasenya())) {
                vereificado = usuario.getVerificado();
                mensaje = "Usuario Logueado";

                //Usuario sin token
                if (usuario.getToken() == null) {
                    apiKey = jwTservice.generateToken(usuario);
                    Token token = new Token();
                    token.setUsuario(usuario);
                    token.setToken(apiKey);
                    token.setFechaExpiracion(LocalDateTime.now().plusDays(1));
                    tokenServicio.save(token);

                    //Usuario con token caducado
                } else if (usuario.getToken().getFechaExpiracion().isBefore(LocalDateTime.now())) {
                    Token token = usuario.getToken();
                    apiKey = jwTservice.generateToken(usuario);
                    token.setToken(apiKey);
                    token.setFechaExpiracion(LocalDateTime.now().plusDays(1));
                    tokenServicio.save(token);

                    //Usuario con token válido
                } else {
                    apiKey = usuario.getToken().getToken();
                }
            } else {
                mensaje = "Contraseña no válida";
            }
        } else {
            mensaje = "Usuario No encontrado";
            vereificado = null;
        }
        return AuthDTO
                .builder()
                .token(apiKey)
                .info(mensaje)
                .verificado(vereificado)
                .build();
    }
    @PostMapping("/register")
    public AuthDTO register(@RequestBody CivilCrearDTO dto) throws MessagingException {

        Civil civil = civilServicio.crearCivil(dto);

        String token = jwTservice.generateToken(civil.getUsuario());

        return AuthDTO
                .builder()
                .token(token)
                .info("Usuario creado correctamente")
                .build();
    }

}
