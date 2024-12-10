package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.PersonaCrearDTO;
import com.example.nexosapp.DTO.PersonaDTO;
import com.example.nexosapp.servicios.PersonaServicio;
import com.example.nexosapp.modelos.Persona;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Persona", description = "Operaciones relacionadas con las personas")
@RestController
@RequestMapping("/persona")
@AllArgsConstructor
public class PersonaControlador {
    private final PersonaServicio personaServicio;

    @Operation(summary = "Obtener todas las personas")
    @GetMapping("/all")
    public List<Persona> getAllPersonas(){
        List<Persona> personas = personaServicio.getAll();
        return personas;
    }
    @Operation(summary = "Obtener una persona por id")
    @GetMapping()
    public Persona getById(@RequestParam Integer id){
        Persona persona = personaServicio.getById(id);
        return persona;
    }
    @Operation(summary = "Guardar una persona")
    @PostMapping()
    public Persona guardar(@RequestBody Persona persona){
        Persona personaGuardado = personaServicio.guardar(persona);
        return personaGuardado;
    }
    @Operation(summary = "Eliminar una persona")
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return personaServicio.eliminar(id);
    }
}
