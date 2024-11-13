package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.AvisoDTO;
import com.example.nexosapp.DTO.CrearAvisoDTO;
import com.example.nexosapp.DTO.FotoUrlDTO;
import com.example.nexosapp.modelos.Aviso;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.recursos.CloudinaryService;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.repositorios.AvisoRepositorio;
import com.example.nexosapp.seguridad.JWTservice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AvisoServicio {
    private final FotoServicio fotoServicio;
    private AvisoRepositorio avisoRepositorio;
    private JWTservice jwTservice;

    private UsuarioService usuarioService;

    private CloudinaryService cloudinaryService;

    public List<Aviso> getAvisos(){
        return avisoRepositorio.findAll();
    }

    public Aviso getAvisoId(Integer id){
        return avisoRepositorio.findById(id).orElse(null);
    }

    public Aviso guardar(Aviso aviso){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Asegurarse de que hay una autenticación válida
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("Usuario no autenticado");
        }
        // Extraer el token del encabezado Authorization
        String token = authentication.getCredentials().toString();
        // Usar el servicio JWT para obtener el idUsuario del token
        Integer idUsuario = jwTservice.extractTokenData(token).getIdUsuario();
        return avisoRepositorio.save(aviso);
    }



    public String eliminar(Integer id) {
        String mensaje;
        Aviso aviso = getAvisoId(id);
        if (aviso == null){
            return "No existe ese aviso";
        }
        try {

            for(Foto f : aviso.getFotos()){
                aviso.getFotos().remove(f);
            }
            avisoRepositorio.saveAndFlush(aviso);
            avisoRepositorio.deleteById(id);
            aviso = getAvisoId(id);
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

    /**
     * Muestra todos los avisos para la pagina principal
     */


    public List<AvisoDTO> getAll(){
        List<AvisoDTO> avisoDTOS= new ArrayList<>();
        List<Aviso> avisos = avisoRepositorio.findAll();

        for (Aviso a : avisos){
            AvisoDTO avisoDTO = new AvisoDTO();
            avisoDTO.setFecha(a.getFecha());
            avisoDTO.setTexto(a.getTexto());
            Set<FotoUrlDTO> fotoUrlDTO = new HashSet<>();
            for (Foto f : a.getFotos()){
                FotoUrlDTO fotoDTO = new FotoUrlDTO();
                fotoDTO.setUrl(f.getUrl());
                fotoUrlDTO.add(fotoDTO);
            }
            avisoDTO.setFotos(fotoUrlDTO);
            avisoDTO.setUsuarioId(a.getUsuario().getId());

            avisoDTOS.add(avisoDTO);
        }
        return avisoDTOS;
    }

    /**
     * Crear un aviso
     */

    public Aviso nuevoAviso(CrearAvisoDTO avisoDTO, List<MultipartFile> files) throws IOException {
        Aviso avisosave = new Aviso();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(avisoDTO.getFecha(), formatter);


        avisosave.setFecha(fecha);
        avisosave.setTexto(avisoDTO.getTexto());
        avisosave.setUsuario(usuarioService.getUsuarioId(avisoDTO.getId_usuario()));

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
            avisosave.setFotos(listaFotos);

        }

        return avisoRepositorio.save(avisosave);
    }
}
