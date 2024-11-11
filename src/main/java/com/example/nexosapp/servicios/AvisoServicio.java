package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.AvisoDTO;
import com.example.nexosapp.DTO.CrearAvisoDTO;
import com.example.nexosapp.DTO.FotoUrlDTO;
import com.example.nexosapp.modelos.Aviso;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.recursos.CloudinaryService;
import com.example.nexosapp.repositorios.AvisoRepositorio;
import lombok.AllArgsConstructor;
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
    private AvisoRepositorio avisoRepositorio;

    private UsuarioService usuarioService;

    private CloudinaryService cloudinaryService;

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
            return "No existe ese aviso";
        }
        try {
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

        Set<Foto> listaFotos = new HashSet<>();
        for (MultipartFile f : files){
            Foto foto = new Foto();
            foto.setUrl(cloudinaryService.uploadImage(f));
            foto.setEsCara(false);
            listaFotos.add(foto);

        }
        avisosave.setFotos(listaFotos);

        return avisosave;
    }
}
