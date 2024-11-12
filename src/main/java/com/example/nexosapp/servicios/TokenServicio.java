package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Token;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.repositorios.TokenRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServicio {
    private final TokenRepositorio tokenRepositorio;


    public Token getByUsuario(Usuario usuario){
        return tokenRepositorio.findTopByUsuario(usuario);
    }

    public Token save(Token token){
        return tokenRepositorio.save(token);
    }
}
