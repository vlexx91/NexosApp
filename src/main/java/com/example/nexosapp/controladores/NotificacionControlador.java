package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.NotificacionCampanaDTO;
import com.example.nexosapp.servicios.NotificacionServicio;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacion")
@AllArgsConstructor
public class NotificacionControlador {

    private NotificacionServicio notificacionServicio;

    @GetMapping("/listar")
    public List<NotificacionCampanaDTO> getAllNotificacionesUsuario(HttpServletRequest request){
        return notificacionServicio.getNotificacionesCampana(request);
    }

    @PutMapping("/modNotificacion")
    public ResponseEntity<String> notificacionLeida(@RequestParam Integer idNotificacion){
        String resultado = notificacionServicio.notificaionLeida(idNotificacion);
        return ResponseEntity.ok(resultado);
    }
}
