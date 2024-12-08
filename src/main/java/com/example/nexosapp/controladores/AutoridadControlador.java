package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.CrearAutoridadDTO;
import com.example.nexosapp.modelos.Autoridad;
import com.example.nexosapp.servicios.AutoridadServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Autoridad", description = "Operaciones relacionadas con los usuarios de tipo autoridad")
@RestController
@RequestMapping("/autoridad")
@AllArgsConstructor
public class AutoridadControlador {

    private AutoridadServicio autoridadServicio;

    @Operation(summary = "Obtener todas las autoridades")
    @GetMapping("/listar")
    public List<Autoridad> getAllAutoridades(){
        return autoridadServicio.getAutoridades();
    }

    @Operation(summary = "Obtener una autoridad por id")
    @GetMapping()
    public Autoridad getById(@RequestParam Integer id){
        return autoridadServicio.getAutoridadId(id);
    }

    @Operation(summary = "Guardar una autoridad")
    @PostMapping()
    public Autoridad guardar(@RequestBody Autoridad autoridad){
        return autoridadServicio.guardar(autoridad);
    }

    @Operation(summary = "eliminar una autoridad")
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return autoridadServicio.eliminar(id);
    }

    @Operation(summary = "crear una autoridad")
    @PostMapping("/crearAutoridad")
    public Autoridad crearAutoridad (@RequestBody CrearAutoridadDTO crearAutoridadDTO) {return autoridadServicio.crearAutoridad(crearAutoridadDTO);}
}
