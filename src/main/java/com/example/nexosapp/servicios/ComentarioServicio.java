package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.ComentarioDTO;
import com.example.nexosapp.modelos.Comentario;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.recursos.CloudinaryService;
import com.example.nexosapp.repositorios.ComentarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ComentarioServicio {

    private ComentarioRepositorio comentarioRepositorio;
    private DesaparicionServicio desaparicionServicio;
    private CloudinaryService cloudinaryService;

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

    /**
     * Crea un comentario en la base de datos
     * @param comentarioDTO
     * @param files
     * @return
     * @throws IOException
     */
    public Comentario crearComentario(ComentarioDTO comentarioDTO, List<MultipartFile> files) throws IOException {
        Comentario comentario = new Comentario();
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setTexto(comentarioDTO.getTexto());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setTelefono(comentarioDTO.getTelefono());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(LocalDate.now().toString(), formatter);
        comentario.setFecha(fecha);
        comentario.setDesaparicion(desaparicionServicio.getDesaparicionId(comentarioDTO.getDesaparicionId()));
        if (files != null && files.stream().anyMatch(file -> !file.isEmpty())) {
            Set<Foto> listaFotos = new HashSet<>();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    Foto foto = new Foto();
                    foto.setUrl(cloudinaryService.uploadImage(file));
                    foto.setEsCara(false);
                    listaFotos.add(foto);
                }
            }
            comentario.setFotos(listaFotos);
        }

        comentarioRepositorio.save(comentario);
        return comentario;
    }




}
