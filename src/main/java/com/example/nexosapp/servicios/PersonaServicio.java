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
     *
     * @return
     */
    public List<PersonaDTO> getAll() {
        List<Persona> personas = personaRepositorio.findAll();
        List<PersonaDTO> personasDTO = new ArrayList<>();

        for (Persona p : personas) {
            PersonaDTO dto = new PersonaDTO();
            dto.setNombre(p.getNombre());
            dto.setApellido(p.getApellido());
            dto.setFechaNacimiento(p.getFechaNacimiento());
            dto.setSexo(p.getSexo());
            dto.setComplexion(p.getComplexion());
            dto.setDescripcion(p.getDescripcion());
        }
        return personasDTO;
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
     *
     * @param dto
     * @return
     */
    public Persona guardar(PersonaCrearDTO dto) {
        Persona personaGuardada = new Persona();
        personaGuardada.setNombre(dto.getNombre());
        personaGuardada.setApellido(dto.getApellido());
        personaGuardada.setFechaNacimiento(dto.getFechaNacimiento());
        personaGuardada.setSexo(dto.getSexo());
        personaGuardada.setComplexion(dto.getComplexion());
        personaGuardada.setDescripcion(dto.getDescripcion());
        return personaRepositorio.save(personaGuardada);
    }

    /**
     *
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
     *
     * @param persona
     */
    public void eliminar(Persona persona){
        personaRepositorio.delete(persona);
    }

}
