package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.PersonaCrearDTO;
import com.example.nexosapp.DTO.PersonaDTO;
import com.example.nexosapp.servicios.PersonaServicio;
import com.example.nexosapp.modelos.Persona;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
@AllArgsConstructor
public class PersonaControlador {
    private final PersonaServicio personaServicio;

    @GetMapping("/all")
    public List<PersonaDTO> getAllPersonas(){
        List<PersonaDTO> personaDTOS = personaServicio.getAll();
        return personaDTOS;
    }

    @GetMapping()
    public Persona getById(@RequestParam Integer id){
        Persona persona = personaServicio.getById(id);
        return persona;
    }
    @GetMapping("/id/{id}")
    public Persona getByIdPath(@PathVariable Integer id){
        Persona persona = personaServicio.getById(id);
        return persona;
    }
    @PostMapping()
    public Persona guardar(@RequestBody PersonaCrearDTO monitor){
        Persona personaGuardado = personaServicio.guardar(monitor);
        return personaGuardado;
    }
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return personaServicio .eliminar(id);
    }
}
