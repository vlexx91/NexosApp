package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Lugar;
import com.example.nexosapp.repositorios.LugarRepositorio;
import com.example.nexosapp.repositorios.UsuarioRepositorio;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarServicio {


    @Autowired
    private LugarRepositorio lugarRepositorio;

    /**
     * Obtener todos los lugares
     * @return
     */

    public List<Lugar> getLugares() {
        List<Lugar> lugar = lugarRepositorio.findAll();
        return lugar;
    }

    /**
     * Obtener lugares por ID
     */

    public Lugar getLugarId(Integer id) {
        return lugarRepositorio.findById(id).orElse(null);
    }

    /**
     * Guarda o edita un lugar
     */

    public Lugar guardar(Lugar lugar){
        return lugarRepositorio.save(lugar);
    }

    /**
     * Eliminar un lugar por ID
     */

    public String eliminaLugarId(Integer id) {
        String mensaje;
        Lugar lugar = lugarRepositorio.findById(id).orElse(null);

        if (lugar == null) {
            mensaje = "Ese lugar no existe";
            return mensaje;
        }

        try {
            lugarRepositorio.deleteById(id);
            Lugar lugartry = lugarRepositorio.findById(id).orElse(null);

            if (lugartry != null) {
                mensaje = "No se ha podido eliminar el lugar";
            } else {
                mensaje = "Lugar eliminado";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar el lugar";
        }

        return mensaje;
    }
}
