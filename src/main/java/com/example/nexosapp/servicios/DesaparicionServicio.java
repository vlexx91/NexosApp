package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.*;
import com.example.nexosapp.mapeadores.DesaparicionMapeador;
import com.example.nexosapp.mapeadores.LugarMapeador;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.modelos.Lugar;
import com.example.nexosapp.recursos.OpenCageService;
import com.example.nexosapp.repositorios.DesaparicionRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DesaparicionServicio {
    private DesaparicionRepositorio desaparicionRepositorio;
    private DesaparicionMapeador desaparicionMapeador;
    private LugarMapeador lugarMapeador;
    private LugarServicio lugarServicio;
    private OpenCageService openCageService;

    /**
     * Método que devuelve una lista de desapariciones
     * @return
     */

    public List<Desaparicion> getDesapariciones(){
        return desaparicionRepositorio.findAll();
    }

    /**
     * Método que devuelve una desaparición por su id
     * @param id
     * @return
     */
    public Desaparicion getDesaparicionId(Integer id){
        return desaparicionRepositorio.findById(id).orElse(null);
    }

    /**
     * Método que guarda una desaparición
     * @param desaparicion
     * @return
     */
    public Desaparicion guardar(Desaparicion desaparicion){
        return desaparicionRepositorio.save(desaparicion);
    }

    /**
     * Método que elimina una desaparición por su id
     * @param id
     * @return
     */
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

    /**
     * Método que guarda una desaparición a partir de un dto, guarda en cascada personal lugar y fotos y conecta con las apis para obtener las coordenadas y subir la foto
     * @param dto
     * @return
     */
    public Desaparicion guardarDesaparicion(DesaparicionDTO dto){
        Desaparicion desaparicion = desaparicionMapeador.toEntity(dto);
        Map<String,Double> coordenadas = openCageService.getLatLon(desaparicion.getLugar().getCalle()+ ", "+ desaparicion.getLugar().getLocalidad() + ", " + desaparicion.getLugar().getProvincia() + ", España");
        desaparicion.getLugar().setLatitud(coordenadas.get("lat"));
        desaparicion.getLugar().setLongitud(coordenadas.get("lon"));
        desaparicionRepositorio.save(desaparicion);
        return desaparicion;
    }

    /**
     * Metodo que a partir de todas las desapariciones, devuelve una lista con una
     * dto preparada paara mostrarla en la página mostrar mas
     * @return List<DesaparionMostrarMasDTO>
     */

    public List<DesaparionMostrarMasDTO> mostrarMas(){
        List<Desaparicion> desapariciones = desaparicionRepositorio.findAll();
        List<String> fotos = new ArrayList<>();
        List<DesaparionMostrarMasDTO> devolucion = new ArrayList<>();
        for (Desaparicion desaparicion : desapariciones){
            DesaparionMostrarMasDTO d = new DesaparionMostrarMasDTO();
            d.setId(desaparicion.getId());
            d.setNombre(desaparicion.getPersona().getNombre());
            d.setApellido(desaparicion.getPersona().getApellido());
            d.setDescripcion(desaparicion.getDescripcion());
            d.setFecha(desaparicion.getFecha().toString());
            LugarDTO l = new LugarDTO(desaparicion.getLugar().getProvincia()
                    ,desaparicion.getLugar().getLocalidad()
                    ,desaparicion.getLugar().getCalle());
            d.setLugar(l);
            desaparicion.getPersona().getFotos().forEach(f->fotos.add(f.getUrl()));
            d.setFotos(fotos);
            devolucion.add(d);
        }
        return devolucion;
    }

    /**
     * Metodo que a partir de todas las desapariciones, devuelve una lista con una
     * dto preparada paara mostrarla en la página principal
     *
     * @return List<DesaparicionPrincipalDTO>
     */
    public List<DesaparicionPrincipalDTO> paginaPrincipal(){
        List<Desaparicion> desapariciones = desaparicionRepositorio.findAll();
        List<DesaparicionPrincipalDTO> devolucion = new ArrayList<>();

        desapariciones.forEach(d->{
            DesaparicionPrincipalDTO des = new DesaparicionPrincipalDTO();
            des.setId(d.getId());
            des.setNombre(d.getPersona().getNombre());
            des.setApellidos(d.getPersona().getApellido());
            des.setFecha(d.getFecha().toString());
            des.setUrlFotoCara(d.getPersona().getFotos()
                    .stream()
                    .filter(Foto::getEsCara)
                    .findFirst()
                    .get().getUrl());
            devolucion.add(des);
        });
        return devolucion;
    }
}

