package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Aviso;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.repositorios.AvisoRepositorio;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvisoServicio {
    private final FotoServicio fotoServicio;
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

            for(Foto f : aviso.getFotos()){
                aviso.getFotos().remove(f);
            }
            avisoRepositorio.saveAndFlush(aviso);
            if (aviso!= null){
                mensaje = "No se ha podido eliminar el aviso.";
            } else {
                mensaje = "Eliminado correctamente.";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar el aviso.";
        }
        return mensaje;
    }
}
