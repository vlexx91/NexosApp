package com.example.nexosapp.repositorios;

import com.example.nexosapp.modelos.Civil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CivilRepositorio extends JpaRepository<Civil, Integer> {
}
