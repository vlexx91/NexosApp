package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.repositorios.FotoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FotoServicio {


    private FotoRepositorio fotoRepositorio;

    /**
     * Guarda una foto en la base de datos, sea nueva o actualiza una existente
     * @param foto
     */
    public Foto guardar(Foto foto){
         return fotoRepositorio.save(foto);
    }

    /**
     * Obtiene todas las fotos de la base de datos
     * @return
     */
    public List<Foto> getFotos() {
        return fotoRepositorio.findAll();
    }

    /**
     * Obtiene una foto por su id
     * @param id
     * @return
     */
    public Foto getFoto(Integer id) {
        return fotoRepositorio.findById(id).orElse(null);
    }

    /**
     * Obtiene una foto por su id y la elimina de la base de datos
     * @param id
     */

    public String eliminar(Integer id) {
        String mensaje;
        Foto foto = getFoto(id);

        if (foto == null) {
            return "no existe esa foto";
        }
        try {
            fotoRepositorio.delete(foto);
            mensaje = "Foto eliminada";
        } catch (Exception e) {
            mensaje = "Error al eliminar la foto";
        }
        return mensaje;
    }

    /**
     * Elimina una foto de la base de datos
     * @param foto
     */
    public void eliminar(Foto foto) {
        fotoRepositorio.delete(foto);
    }





}
