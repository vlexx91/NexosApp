package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.repositorios.DesaparicionRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DesaparicionPrincipalServicio {
    private DesaparicionRepositorio desaparicionRepositorio;

    public List<Desaparicion> obtenerUltimos10Desaparecidos(){
        return desaparicionRepositorio.findTop10ByOrderByFechaDesc();
    }

}
