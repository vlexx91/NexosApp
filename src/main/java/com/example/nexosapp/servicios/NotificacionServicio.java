package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.NotificacionCampanaDTO;
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

    /**
     * Obtiene todas las notificaciones
     * @param id
     * @return notificacion
     */
    public Notificacion getNotificacion(Integer id) {
        return notificacionRepositorio.findById(id).orElse(null);
    }
    /**
     * Guarda una notificacion
     * @param notificacion
     */
    public void saveNotificacion(Notificacion notificacion) {
        notificacionRepositorio.save(notificacion);
    }
    /**
     * Elimina una notificacion
     * @param request
     * @return lista de notificaciones
     */
    public List<NotificacionCampanaDTO> getNotificacionesCampana(HttpServletRequest request) {
        Usuario usuario = usuarioService.getUsuarioId(jwTservice.extraerDatosHeader(request).getIdUsuario());
        List<Notificacion> notificaciones = notificacionRepositorio.findAllByUsuarioAndLeidaFalseOrderByFechaAsc(usuario);
        return notificaciones.stream().map(notificacion -> new NotificacionCampanaDTO(notificacion.getId(), notificacion.getDesaparicion().getId(), notificacion.getTipo(), notificacion.getFecha().toString(), notificacion.getTexto())).toList();
    }
    /**
     * Cambia el estado de una notificacion a leida
     * @param idNotificacion
     * @return mensaje
     */
    public String notificaionLeida(Integer idNotificacion){
        Notificacion notificacion = notificacionRepositorio.getReferenceById(idNotificacion);
        notificacion.setLeida(true);
        try {
            notificacionRepositorio.save(notificacion);
            return "Notificacion modificada";
        } catch (Exception e){
            return "Error al modificar notificacion";
        }

    }
}
