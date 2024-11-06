package com.example.nexosapp.repositorios;

import com.example.nexosapp.modelos.Aviso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoRepositorio extends JpaRepository<Aviso, Integer> {
}
