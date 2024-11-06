package com.example.nexosapp.repositorios;

import com.example.nexosapp.modelos.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto,Integer> {
}
