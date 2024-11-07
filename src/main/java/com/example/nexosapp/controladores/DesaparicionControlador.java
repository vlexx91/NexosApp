package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.DesaparicionDTO;
import com.example.nexosapp.DTO.DesaparionMostrarMasDTO;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.servicios.DesaparicionServicio;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desaparicion")
@AllArgsConstructor
public class DesaparicionControlador {

    private DesaparicionServicio desaparicionServicio;

    @GetMapping("/listar")
    public List<Desaparicion> getAllDesapariciones(){
        return desaparicionServicio.getDesapariciones();
    }

    @GetMapping()
    public Desaparicion getById(@RequestParam Integer id){
        return desaparicionServicio.getDesaparicionId(id);
    }
    @PostMapping()
    public Desaparicion guardar(@RequestBody Desaparicion desaparicion){
        return desaparicionServicio.guardar(desaparicion);
    }

    @PostMapping("/guardar")
    public Desaparicion guardarDesaparicion(@RequestBody DesaparicionDTO desaparicion){
        return desaparicionServicio.guardarDesaparicion(desaparicion);
    }
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return desaparicionServicio.eliminar(id);
    }

    @GetMapping("/mostrarMas")
    public List<DesaparionMostrarMasDTO> mostrarMas(){
        return desaparicionServicio.mostrarMas();
    }
}
