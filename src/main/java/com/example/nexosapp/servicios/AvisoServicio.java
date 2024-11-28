package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.AvisoDTO;
import com.example.nexosapp.DTO.AvisoDTOAutoridadAdmin;
import com.example.nexosapp.DTO.CrearAvisoDTO;
import com.example.nexosapp.DTO.FotoUrlDTO;
import com.example.nexosapp.modelos.Aviso;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.recursos.CloudinaryService;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.repositorios.AvisoRepositorio;
import com.example.nexosapp.seguridad.JWTservice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (aviso == null) {
            return "No existe ese aviso";
        }

        try {
            // Si necesitas romper la relación entre Aviso y Fotos:
            aviso.getFotos().clear();
            avisoRepositorio.saveAndFlush(aviso);

            // Ahora elimina el aviso
            avisoRepositorio.deleteById(id);

            // Verifica si el aviso aún existe
            if (avisoRepositorio.existsById(id)) {
                mensaje = "No se ha podido eliminar el aviso.";
            } else {
                mensaje = "Eliminado correctamente.";
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            avisoDTO.setId_usuario(a.getUsuario().getId());

            avisoDTOS.add(avisoDTO);
        }
        return avisoDTOS;
    }

    /**
     * Crear un aviso
     */

    public ResponseEntity<String> nuevoAviso(HttpServletRequest request, CrearAvisoDTO avisoDTO, List<MultipartFile> files) throws IOException {
        Aviso avisosave = new Aviso();
        Integer id = jwTservice.extraerDatosHeader(request).getIdUsuario();

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate fecha = LocalDate.parse(avisoDTO.getFecha(), formatter);


        avisosave.setFecha(LocalDateTime.now());
        avisosave.setTexto(avisoDTO.getTexto());
        avisosave.setUsuario(usuarioService.getUsuarioId(id));

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

        avisoRepositorio.save(avisosave);

        return ResponseEntity.ok("Aviso creado con éxito") ;
    }


    /**
     * Lista avisos para AvisoDTOAutoridad
     */

    public List<AvisoDTOAutoridadAdmin> listarAdminAvisos(){
        List<AvisoDTOAutoridadAdmin> avisoDTOS= new ArrayList<>();
        List<Aviso> avisos = avisoRepositorio.findAll();

        for (Aviso a : avisos){
            AvisoDTOAutoridadAdmin avisoDTO = new AvisoDTOAutoridadAdmin();
            avisoDTO.setFecha(a.getFecha().toLocalDate());
            avisoDTO.setTexto(a.getTexto());
            avisoDTO.setId(a.getId());
            avisoDTO.setUsername(usuarioService.getUsuarioId(a.getUsuario().getId()).getUsername());

            avisoDTOS.add(avisoDTO);
        }
        return avisoDTOS;
    }

}
