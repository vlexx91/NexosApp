package com.example.nexosapp.repositorios;

import com.example.nexosapp.modelos.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisoRepositorio extends JpaRepository<Aviso, Integer> {
    List<Aviso> findAvisoByUsuario_Id(Integer usuarioId);
}
