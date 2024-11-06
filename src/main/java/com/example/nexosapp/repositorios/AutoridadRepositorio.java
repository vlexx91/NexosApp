package com.example.nexosapp.repositorios;

import com.example.nexosapp.modelos.Autoridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoridadRepositorio extends JpaRepository<Autoridad, Integer> {
}
