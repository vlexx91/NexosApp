package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.NotificacionCampana;
import com.example.nexosapp.servicios.NotificacionServicio;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notificacion")
@AllArgsConstructor
public class NotificacionControlador {

    private NotificacionServicio notificacionServicio;

    @GetMapping("/listar")
    public List<NotificacionCampana> getAllNotificacionesUsuario(HttpServletRequest request){
        return notificacionServicio.getNotificacionesCampana(request);
    }
}
