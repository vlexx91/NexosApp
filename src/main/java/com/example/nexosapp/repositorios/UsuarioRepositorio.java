package com.example.nexosapp.repositorios;

import com.example.nexosapp.enumerados.ROL;
import com.example.nexosapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findTopByUsuario(String usuario);
    List<Usuario> findByDesapariciones_Id(Integer desaparicionId);
    List<Usuario> findByRol(ROL rol);
}
