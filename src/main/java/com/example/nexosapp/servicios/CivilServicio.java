package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.repositorios.CivilRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CivilServicio {

    private CivilRepositorio civilRepositorio;

    /**
     * Guardar civil
     * @param civil
     * @return
     */
    public Civil guardar(Civil civil){
        return civilRepositorio.save(civil);
    }

    /**
     * Obtener todos los civiles
     * @return
     */
    public List<Civil>getCiviles(){
        return civilRepositorio.findAll();
    }

    /**
     * Obtener civil por id
     * @param id
     * @return
     */
    public Civil getCivilId(Integer id){
        return civilRepositorio.findById(id).orElse(null);
    }


    /**
     * eliminar civil por id
     * @param id
     * @return
     */
    public String eliminar(Integer id){
        String mensaje;
        Civil civil = getCivilId(id);

        if (civil == null){
            return "no existe ese civil";
        }
        try {
            civilRepositorio.deleteById(id);
            civil = getCivilId(id);
            if (civil!= null){
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
