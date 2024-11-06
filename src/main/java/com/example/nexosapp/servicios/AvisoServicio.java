package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Aviso;
import com.example.nexosapp.repositorios.AvisoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvisoServicio {
    private AvisoRepositorio avisoRepositorio;

    public List<Aviso> getAvisos(){
        return avisoRepositorio.findAll();
    }

    public Aviso getAvisoId(Integer id){
        return avisoRepositorio.findById(id).orElse(null);
    }

    public Aviso guardar(Aviso aviso){
        return avisoRepositorio.save(aviso);
    }

    public String eliminar(Integer id) {
        String mensaje;
        Aviso aviso = getAvisoId(id);
        if (aviso == null){
            return "No existe ese usuario";
        }
        try {
            avisoRepositorio.deleteById(id);
            aviso = getAvisoId(id);
            if (aviso!= null){
                mensaje = "No se ha podido eliminar la desaparición.";
            } else {
                mensaje = "Eliminado correctamente.";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar la desaparición.";
        }
        return mensaje;
    }
}
