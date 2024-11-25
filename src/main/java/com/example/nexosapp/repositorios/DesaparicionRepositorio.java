package com.example.nexosapp.repositorios;

import com.example.nexosapp.enumerados.Complexion;
import com.example.nexosapp.enumerados.ESTADO;
import com.example.nexosapp.enumerados.Sexo;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.modelos.Lugar;
import com.example.nexosapp.modelos.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DesaparicionRepositorio extends JpaRepository<Desaparicion, Integer> {
    List<Desaparicion> findTop10ByEliminadaIsFalseAndAprobadaIsTrueAndEstadoOrderByFechaDesc(ESTADO estado);
    List<Desaparicion> findAllByEliminadaIsFalse();
    List<Desaparicion> findAllByAprobadaIsFalseAndEliminadaIsFalse();
    List<Desaparicion> findAllByEliminadaIsTrue();



    @Query("SELECT d FROM Desaparicion d WHERE d.aprobada = false")
    List<Desaparicion> desaparicionesPendientes();

//    @Query("""
//            SELECT d FROM Desaparicion d
//            LEFT JOIN d.persona p
//            LEFT JOIN d.lugar l
//            WHERE (:fecha IS NULL OR d.fecha = :fecha)
//            AND (:estado IS NULL OR d.estado = :estado)
//            AND (:provincia IS NULL OR l.provincia LIKE CONCAT('%', :provincia, '%'))
//            AND (:dni IS NULL OR p.dni LIKE CONCAT('%', :dni, '%'))
//            AND (:nombrePersona IS NULL OR p.nombre LIKE CONCAT('%', :nombrePersona, '%'))
//            AND (:apellidoPersona  OR p.apellido LIKE CONCAT('%', :apellidoPersona, '%'))
//            AND (:sexo IS NULL OR p.sexo = :sexo)
//            """)
//    List<Desaparicion> buscarDesapariciones(
//            @Param("fecha") LocalDate fecha,
//            @Param("estado") Integer estado,
//            @Param("provincia") String provincia,
//            @Param("dni") String dni,
//            @Param("nombrePersona") String nombrePersona,
//            @Param("apellidoPersona") String apellidoPersona,
//            @Param("sexo") Integer sexo
//    );

    @Query("SELECT d FROM Desaparicion d " +
            "JOIN d.persona p " +
            "JOIN d.lugar l " +
            "WHERE (:dni IS NULL OR p.dni = :dni) " +
            "AND (:nombre IS NULL OR p.nombre LIKE %:nombre%) " +
            "AND (:apellidos IS NULL OR p.apellido LIKE %:apellidos%) " +
            "AND (:sexo IS NULL OR p.sexo = :sexo) " +
            "AND (:complexion IS NULL OR p.complexion = :complexion) " +
            "AND (:estado IS NULL OR d.estado = :estado) " +
            "AND (:provincia IS NULL OR l.provincia LIKE %:provincia%) " +
            "AND (:localidad IS NULL OR l.localidad LIKE %:localidad%) " +
            "AND (:fechaDesde IS NULL OR d.fecha >= :fechaDesde) " +
            "AND (:fechaHasta IS NULL OR d.fecha <= :fechaHasta)")
    List<Desaparicion> buscarConFiltros(String dni, String nombre, String apellidos, Sexo sexo, Complexion complexion,
                                        ESTADO estado, String provincia, String localidad, LocalDate fecha);
}



