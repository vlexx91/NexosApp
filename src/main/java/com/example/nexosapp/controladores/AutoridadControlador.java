package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.CrearAutoridadDTO;
import com.example.nexosapp.modelos.Autoridad;
import com.example.nexosapp.servicios.AutoridadServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autoridad")
@AllArgsConstructor
public class AutoridadControlador {

    private AutoridadServicio autoridadServicio;

    @GetMapping("/listar")
    public List<Autoridad> getAllAutoridades(){
        return autoridadServicio.getAutoridades();
    }

    @GetMapping()
    public Autoridad getById(@RequestParam Integer id){
        return autoridadServicio.getAutoridadId(id);
    }

    @PostMapping()
    public Autoridad guardar(@RequestBody Autoridad autoridad){
        return autoridadServicio.guardar(autoridad);
    }

    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return autoridadServicio.eliminar(id);
    }

    @PostMapping("/crearAutoridad")
    public Autoridad crearAutoridad (@RequestBody CrearAutoridadDTO crearAutoridadDTO) {return autoridadServicio.crearAutoridad(crearAutoridadDTO);}
}
