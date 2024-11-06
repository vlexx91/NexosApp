package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.repositorios.DesaparicionRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DesaparicionServicio {
    private DesaparicionRepositorio desaparicionRepositorio;

    public List<Desaparicion> getDesapariciones(){
        return desaparicionRepositorio.findAll();
    }

    public Desaparicion getDesaparicionId(Integer id){
        return desaparicionRepositorio.findById(id).orElse(null);
    }

    public Desaparicion guardar(Desaparicion desaparicion){
        return desaparicionRepositorio.save(desaparicion);
    }

    public String eliminar(Integer id) {
        String mensaje;
        Desaparicion desaparicion = getDesaparicionId(id);
        if (desaparicion == null){
            return "No existe ese usuario";
        }
        try {
            desaparicionRepositorio.deleteById(id);
            desaparicion = getDesaparicionId(id);
            if (getDesaparicionId(id)!= null){
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

