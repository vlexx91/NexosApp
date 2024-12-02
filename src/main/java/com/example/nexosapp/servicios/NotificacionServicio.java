package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.NotificacionCampana;
import com.example.nexosapp.modelos.Notificacion;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.repositorios.NotificacionRepositorio;
import com.example.nexosapp.seguridad.JWTservice;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificacionServicio {
    private NotificacionRepositorio notificacionRepositorio;
    private JWTservice jwTservice;
    private UsuarioService usuarioService;

    public Notificacion getNotificacion(Integer id) {
        return notificacionRepositorio.findById(id).orElse(null);
    }
    public Notificacion saveNotificacion(Notificacion notificacion) {
        return notificacionRepositorio.save(notificacion);
    }
    public List<NotificacionCampana> getNotificacionesCampana(HttpServletRequest request) {
        Usuario usuario = usuarioService.getUsuarioId(jwTservice.extraerDatosHeader(request).getIdUsuario());
        List<Notificacion> notificaciones = notificacionRepositorio.findAllByUsuarioAndLeidaFalseOrderByFechaAsc(usuario);
        return notificaciones.stream().map(notificacion -> new NotificacionCampana(notificacion.getId(), notificacion.getDesaparicion().getId(), notificacion.getTipo(), notificacion.getFecha().toString(), notificacion.getTexto())).toList();
    }
}
