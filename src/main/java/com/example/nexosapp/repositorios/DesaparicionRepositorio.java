package com.example.nexosapp.repositorios;

import com.example.nexosapp.enumerados.ESTADO;
import com.example.nexosapp.modelos.Desaparicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesaparicionRepositorio extends JpaRepository<Desaparicion, Integer> {
    List<Desaparicion> findTop10ByEliminadaIsFalseAndEstadoOrderByFechaDesc(ESTADO estado);
    List<Desaparicion> findAllByEliminadaIsFalse();



    @Query("SELECT d FROM Desaparicion d WHERE d.aprobada = false")
    List<Desaparicion> desaparicionesPendientes();

}
