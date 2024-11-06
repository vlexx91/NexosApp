package com.example.nexosapp.servicios;

import com.example.nexosapp.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

   @Autowired
    private UsuarioRepositorio usuarioRepositorio;
}
