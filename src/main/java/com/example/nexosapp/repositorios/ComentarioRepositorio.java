package com.example.nexosapp.repositorios;

import com.example.nexosapp.modelos.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepositorio extends JpaRepository<Comentario,Integer> {
    List<Comentario> findByDesaparicionId(Integer desaparicionId);

}
