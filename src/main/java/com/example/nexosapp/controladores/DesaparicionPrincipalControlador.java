package com.example.nexosapp.controladores;

import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.servicios.DesaparicionPrincipalServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/desaparicionprincipal")
@AllArgsConstructor
public class DesaparicionPrincipalControlador {
    private DesaparicionPrincipalServicio desaparicionPrincipalServicio;

    @GetMapping("/listar")
    public List<Desaparicion> obtenerDesaparecidos() {
        return desaparicionPrincipalServicio.obtenerUltimos10Desaparecidos();
    }

}
