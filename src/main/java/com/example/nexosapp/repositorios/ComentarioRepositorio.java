package com.example.nexosapp.repositorios;

import com.example.nexosapp.modelos.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario,Integer> {
}
