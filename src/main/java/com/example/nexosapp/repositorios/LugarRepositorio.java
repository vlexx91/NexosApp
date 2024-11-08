package com.example.nexosapp.repositorios;

import com.example.nexosapp.DTO.MapaPrincipalDTO;
import com.example.nexosapp.modelos.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LugarRepositorio extends JpaRepository<Lugar, Integer> {
}
