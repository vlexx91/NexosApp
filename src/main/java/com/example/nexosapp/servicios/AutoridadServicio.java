package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Autoridad;
import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.repositorios.AutoridadRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AutoridadServicio {

    private AutoridadRepositorio autoridadRepositorio;

    /**
     * Guardar autoridad
     * @param autoridad
     * @return
     */
    public Autoridad guardar(Autoridad autoridad){
        return autoridadRepositorio.save(autoridad);
    }

    /**
     * Obtener todos las autoridades
     * @return
     */
    public List<Autoridad> getAutoridades(){
        return autoridadRepositorio.findAll();
    }

    /**
     * Obtener autoridad por id
     *
     * @param id
     * @return
     */
    public Autoridad getAutoridadId(Integer id){
        return autoridadRepositorio.findById(id).orElse(null);
    }


    /**
     * eliminar autoridad por id
     * @param id
     * @return
     */
    public String eliminar(Integer id){
        String mensaje;
        Autoridad autoridad = getAutoridadId(id);

        if (autoridad == null){
            return "no existe ese civil";
        }
        try {
            autoridadRepositorio.deleteById(id);
            autoridad = getAutoridadId(id);
            if (autoridad!= null){
                mensaje = "No se ha podido eliminar el civil.";
            } else {
                mensaje = "Eliminado correctamente.";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar el civil.";
        }
        return mensaje;
    }
}
