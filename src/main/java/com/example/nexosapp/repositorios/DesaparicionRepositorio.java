package com.example.nexosapp.repositorios;

import com.example.nexosapp.modelos.Desaparicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesaparicionRepositorio extends JpaRepository<Desaparicion, Integer> {
}
