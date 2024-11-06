package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Comentario;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.repositorios.ComentarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComentarioServicio {

    private ComentarioRepositorio comentarioRepositorio;

    /**
     * Obtiene todas los comentarios de la base de datos
     * @return
     */
    public List<Comentario> getComentarios() {
        return comentarioRepositorio.findAll();
    }

    /**
     * Obtiene un comentario por su id
     * @param id
     * @return
     */
    public Comentario getComentario(Integer id) {
        return comentarioRepositorio.findById(id).orElse(null);
    }

    /**
     * Guarda un comentario en la base de datos, sea nuevo o actualiza uno existente
     * @param comentario
     */
    public Comentario guardar(Comentario comentario) {
        return comentarioRepositorio.save(comentario);
    }

    /**
     * Obtiene un comentario por su id y lo elimina de la base de datos
     * @param id
     */
    public String eliminar(Integer id) {
        String mensaje;
        Comentario comentario = getComentario(id);

        if (comentario == null) {
            return "no existe ese comentario";
        }
        try {
            comentarioRepositorio.delete(comentario);
            mensaje = "Comentario eliminado";
        } catch (Exception e) {
            mensaje = "Error al eliminar el comentario";
        }
        return mensaje;
    }

    /**
     * Elimina un comentario de la base de datos
     * @param comentario
     */
    public void eliminar(Comentario comentario) {
        comentarioRepositorio.delete(comentario);
    }




}
