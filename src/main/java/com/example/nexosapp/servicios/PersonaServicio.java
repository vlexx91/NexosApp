package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.PersonaCrearDTO;
import com.example.nexosapp.DTO.PersonaDTO;
import com.example.nexosapp.modelos.Persona;
import com.example.nexosapp.repositorios.PersonaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonaServicio {
    private PersonaRepositorio personaRepositorio;

    /**
     * Obtener todas las personas
     *
     * @return
     */
    public List<Persona> getAll() {
        List<Persona> personas = personaRepositorio.findAll();
        return personas;
    }

    /**
     *
     * @param id
     * @return
     */
    public Persona getById(Integer id){
        return personaRepositorio.findById(id).orElse(null);
    }

    /**
     * Guardar persona
     * @param persona
     * @return
     */
    public Persona guardar(Persona persona) {
        return personaRepositorio.save(persona);
    }

    /**
     * eliminar persona a traves del id
     * @param id
     * @return
     */
    public String eliminar(Integer id){
        String mensaje;
        Persona persona = getById(id);

        if(persona == null){
            return  "La persona con el id indicado no existe ";
        }

        try {
            personaRepositorio.deleteById(id);
            persona = getById(id);
            if(persona != null){
                mensaje =  "No se ha podido eliminar la persona";
            }else{
                mensaje = "Persona eliminada correctamente";
            }
        } catch (Exception e) {
            mensaje =  "No se ha podido eliminar la persona ";
        }

        return mensaje;
    }

    /**
     * eliminar persona
     * @param persona
     */
    public void eliminar(Persona persona){
        personaRepositorio.delete(persona);
    }

}
