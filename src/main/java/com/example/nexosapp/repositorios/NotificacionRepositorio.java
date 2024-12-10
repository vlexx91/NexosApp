package com.example.nexosapp.repositorios;

import com.example.nexosapp.modelos.Notificacion;
import com.example.nexosapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepositorio extends JpaRepository<Notificacion,Integer> {
    List<Notificacion> findAllByUsuarioAndLeidaFalseOrderByFechaAsc(Usuario usuario);
}
