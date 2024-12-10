package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.ComentarioDTO;
import com.example.nexosapp.DTO.ComentarioListarDTO;
import com.example.nexosapp.DTO.DenunciaComentarioDTO;
import com.example.nexosapp.enumerados.ROL;
import com.example.nexosapp.modelos.*;
import com.example.nexosapp.recursos.CloudinaryService;
import com.example.nexosapp.repositorios.ComentarioRepositorio;
import com.example.nexosapp.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ComentarioServicio {

    private ComentarioRepositorio comentarioRepositorio;
    private DesaparicionServicio desaparicionServicio;
    private CloudinaryService cloudinaryService;
    private NotificacionServicio notificacionServicio;
    private UsuarioRepositorio usuarioRepositorio;

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
    public ResponseEntity<String> eliminar(Integer id) {
        Comentario comentario = getComentario(id);

        if (comentario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe ese comentario");
        }

        try {
            comentarioRepositorio.delete(comentario);
            return ResponseEntity.ok("Comentario eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el comentario");
        }
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
    public ResponseEntity<String> crearComentario(ComentarioDTO comentarioDTO, List<MultipartFile> files) throws IOException {
        Desaparicion desaparicion = desaparicionServicio.getDesaparicionId(comentarioDTO.getDesaparicionId());
        Comentario comentario = new Comentario();
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setTexto(comentarioDTO.getTexto());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setTelefono(comentarioDTO.getTelefono());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(LocalDate.now().toString(), formatter);
        comentario.setFecha(fecha);
        comentario.setDesaparicion(desaparicion);
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
        Notificacion n = new Notificacion();
        n.setDesaparicion(desaparicion);
        n.setUsuario(desaparicion.getUsuario());
        n.setTipo("COMENTARIO");
        n.setFecha(LocalDateTime.now());
        n.setTexto("Se ha añadido un nuevo comentario a la desparicion de " + desaparicion.getPersona().getNombre() + " " + desaparicion.getPersona().getApellido() + ".");
        n.setLeida(false);
        notificacionServicio.saveNotificacion(n);
        return ResponseEntity.ok("Comentario creado");
    }

    /**
     * Obtiene los comentarios de una desaparición por su id y los prepara en una dto
     * @param desaparicionId
     * @return
     */

    public List<ComentarioListarDTO> obtenerComentariosPorDesaparicionId(Integer desaparicionId) {
        List<Comentario> comentarios = comentarioRepositorio.findByDesaparicionId(desaparicionId);
        List<ComentarioListarDTO> comentariosListarDTO = new ArrayList<>();

        comentarios.forEach(comentario -> {
            ComentarioListarDTO comentarioListarDTO = new ComentarioListarDTO();
            comentarioListarDTO.setId(comentario.getId());
            comentarioListarDTO.setTexto(comentario.getTexto());
            comentarioListarDTO.setNombre(comentario.getNombre());
            comentarioListarDTO.setEmail(comentario.getEmail());
            comentarioListarDTO.setTelefono(comentario.getTelefono());
            comentarioListarDTO.setFotos(comentario.getFotos().stream().map(Foto::getUrl).collect(Collectors.toList()));
            comentariosListarDTO.add(comentarioListarDTO);
        });
        return comentariosListarDTO;
    }

    /**
     * Denuncia un comentario y notifica a los administradores
     * @param denunciaComentarioDTO
     * @return
     */
    public ResponseEntity<String> denunciaComentario(DenunciaComentarioDTO denunciaComentarioDTO) {
        Desaparicion desaparicion = desaparicionServicio.getDesaparicionId(denunciaComentarioDTO.getIdDesaparicion());
        List<Usuario> listaAdmin = usuarioRepositorio.findByRol(ROL.ADMIN);
        for (Usuario u : listaAdmin){
            Notificacion n = new Notificacion();
            n.setDesaparicion(desaparicion);
            n.setUsuario(u);
            n.setTipo("DENUNCIA");
            n.setTexto("Se ha denunciado un comentario en la desaparicion de " + desaparicion.getPersona().getNombre() + " " + desaparicion.getPersona().getApellido() + " con el siguiente texto:\n" + denunciaComentarioDTO.getTexto());
            n.setFecha(LocalDateTime.now());
            n.setLeida(false);
            notificacionServicio.saveNotificacion(n);
        }
        return ResponseEntity.ok("Comentario denunciado");
    }

}
