package com.example.nexosapp.repositorios;

import com.example.nexosapp.DTO.CivilConfirmarDTO;
import com.example.nexosapp.modelos.Civil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CivilRepositorio extends JpaRepository<Civil, Integer> {
    Civil findTopByUsuarioId(Integer id);
    @Query("select c from Civil c where c.usuario.verificado = false")
    List<Civil> findUsuariosNoVerificados();
}
