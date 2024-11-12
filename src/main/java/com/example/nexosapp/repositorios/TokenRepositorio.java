package com.example.nexosapp.repositorios;

import com.example.nexosapp.modelos.Token;
import com.example.nexosapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepositorio extends JpaRepository<Token, Integer> {
    Token findTopByUsuario(Usuario usuario);
}
