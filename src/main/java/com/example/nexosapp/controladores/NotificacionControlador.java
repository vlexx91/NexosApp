package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.NotificacionCampanaDTO;
import com.example.nexosapp.servicios.NotificacionServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Notificación", description = "Operaciones relacionadas con los notificaciones")
@RestController
@RequestMapping("/notificacion")
@AllArgsConstructor
public class NotificacionControlador {

    private NotificacionServicio notificacionServicio;

    @Operation(summary = "Obtener lista de notificaciones de un usuario")
    @GetMapping("/listar")
    public List<NotificacionCampanaDTO> getAllNotificacionesUsuario(HttpServletRequest request){
        return notificacionServicio.getNotificacionesCampana(request);
    }

    @Operation(summary = "Combio de estado de notificación a leida")
    @PutMapping("/modNotificacion")
    public ResponseEntity<String> notificacionLeida(@RequestParam Integer idNotificacion){
        String resultado = notificacionServicio.notificaionLeida(idNotificacion);
        return ResponseEntity.ok(resultado);
    }
}
