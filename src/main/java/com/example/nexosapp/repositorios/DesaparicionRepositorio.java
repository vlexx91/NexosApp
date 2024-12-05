package com.example.nexosapp.repositorios;

import com.example.nexosapp.enumerados.Complexion;
import com.example.nexosapp.enumerados.ESTADO;
import com.example.nexosapp.enumerados.Sexo;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.modelos.Lugar;
import com.example.nexosapp.modelos.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DesaparicionRepositorio extends JpaRepository<Desaparicion, Integer> {
    List<Desaparicion> findTop10ByEliminadaIsFalseAndAprobadaIsTrueAndEstadoOrderByFechaDesc(ESTADO estado);

    List<Desaparicion> findAllByEliminadaIsFalse();

    List<Desaparicion> findAllByAprobadaIsFalseAndEliminadaIsFalse();
    List<Desaparicion> findAllByEliminadaIsTrue();


    @Query("SELECT d FROM Desaparicion d WHERE d.aprobada = false")
    List<Desaparicion> desaparicionesPendientes();

    @Query("SELECT d FROM Desaparicion d JOIN FETCH d.persona p LEFT JOIN FETCH p.fotos WHERE d.id = :id")
    Optional<Desaparicion> findByIdWithPhotos(@Param("id") Integer id);


    @Query("SELECT d FROM Desaparicion d JOIN FETCH d.persona p LEFT JOIN FETCH p.fotos " +
            "WHERE (d.fecha = :fecha) " +
            "AND (:estado IS NULL OR d.estado = :estado) " +
            "AND (:nombre IS NULL OR p.nombre ILIKE %:nombre%)" +
            "AND d.aprobada = true")
    List<Desaparicion> buscarPorFechaEstadoYNombre(
            @Param("fecha") LocalDate fecha,
            @Param("estado") ESTADO estado,
            @Param("nombre") String nombre);


    @Query("SELECT d FROM Desaparicion d JOIN FETCH d.persona p LEFT JOIN FETCH p.fotos " +
            "WHERE " +
            "(:estado IS NULL OR d.estado = :estado) " +
            "AND (:nombre IS NULL OR p.nombre ILIKE %:nombre%)"+
            "AND d.aprobada = true")
    List<Desaparicion> buscarPorEstadoYNombre(
            @Param("estado") ESTADO estado,
            @Param("nombre") String nombre);
    @Query("SELECT d FROM Desaparicion d WHERE d.aprobada = true")
    List<Desaparicion> findVerified();
    @Query("SELECT d FROM Desaparicion d WHERE LOWER(d.persona.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) AND d.aprobada = true")
    List<Desaparicion> findByNombreIgnoreCase(@Param("nombre") String nombre);

    @Query("SELECT d FROM Desaparicion d WHERE d.aprobada = true ORDER BY d.fecha DESC")
    Page<Desaparicion> findLast30Verified(Pageable pageable);


}

