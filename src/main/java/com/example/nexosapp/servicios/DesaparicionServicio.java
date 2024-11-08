package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.DesaparicionDTO;
import com.example.nexosapp.mapeadores.DesaparicionMapeador;
import com.example.nexosapp.mapeadores.LugarMapeador;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.modelos.Lugar;
import com.example.nexosapp.recursos.CloudinaryService;
import com.example.nexosapp.recursos.OpenCageService;
import com.example.nexosapp.repositorios.DesaparicionRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class DesaparicionServicio {
    private DesaparicionRepositorio desaparicionRepositorio;
    private DesaparicionMapeador desaparicionMapeador;
    private LugarMapeador lugarMapeador;
    private LugarServicio lugarServicio;
    private OpenCageService openCageService;
    private CloudinaryService cloudinaryService;


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
            if (desaparicion!= null){
                mensaje = "No se ha podido eliminar la desaparición.";
            } else {
                mensaje = "Eliminado correctamente.";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar la desaparición.";
        }
        return mensaje;
    }

    public Desaparicion guardarDesaparicion(DesaparicionDTO dto, List<MultipartFile> files) throws IOException {
        Desaparicion desaparicion = desaparicionMapeador.toEntity(dto);
        Map<String,Double> coordenadas = openCageService.getLatLon(desaparicion.getLugar().getCalle()+ ", "+ desaparicion.getLugar().getLocalidad() + ", " + desaparicion.getLugar().getProvincia() + ", España");
        desaparicion.getLugar().setLatitud(coordenadas.get("lat"));
        desaparicion.getLugar().setLongitud(coordenadas.get("lon"));
        for (MultipartFile f : files){
            Foto foto = new Foto();
            foto.setUrl(cloudinaryService.uploadImage(f));
            foto.setEsCara(false);
            desaparicion.getPersona().getFotos().add(foto);

        }
        desaparicionRepositorio.save(desaparicion);
        return desaparicion;
    }
}

