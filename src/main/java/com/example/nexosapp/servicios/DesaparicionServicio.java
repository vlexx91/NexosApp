package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.*;
import com.example.nexosapp.enumerados.ESTADO;
import com.example.nexosapp.mapeadores.DesaparicionMapeador;
import com.example.nexosapp.mapeadores.LugarMapeador;
import com.example.nexosapp.modelos.*;
import com.example.nexosapp.recursos.CloudinaryService;
import com.example.nexosapp.recursos.OpenCageService;
import com.example.nexosapp.repositorios.DesaparicionRepositorio;
import com.example.nexosapp.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private AutoridadServicio autoridadServicio;
    private UsuarioRepositorio usuarioRepositorio;

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
            //aqui primero limpio las desapariciones que sigue el usuario y luego elimino de las relaciones de segimiento de otros  usuarios con esa desaparicion
            List<Usuario> usuarios = usuarioRepositorio.findByDesapariciones_Id(id);
            for (Usuario u : usuarios){
                u.getDesapariciones().remove(desaparicion);
                usuarioRepositorio.save(u);
            }
            desaparicionRepositorio.deleteById(id);
            desaparicion = getDesaparicionId(id);
            if (desaparicion!= null){
                mensaje = "No se ha podido eliminar la desaparición correcmente.";
            } else {
                mensaje = "Eliminado correctamente.";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar la desaparición.";
        }
        return mensaje;
    }

    public String eliminarDesaparicion(Integer id){
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElse(null);
        if (desaparicion == null){
            return "No existe esa desaparición";
        }
        desaparicion.setEliminada(true);
        return "Desaparición eliminada";
    }

    /**
     * Método que guarda una desaparición a partir de un dto, guarda en cascada personal lugar y fotos y conecta con las apis para obtener las coordenadas y subir la foto
     * @param dto
     * @return
     */
    public Desaparicion guardarDesaparicion(DesaparicionDTO dto, List<MultipartFile> files) throws IOException {
        Desaparicion desaparicion = desaparicionMapeador.toEntity(dto);
        Map<String,Double> coordenadas = openCageService.getLatLon(desaparicion.getLugar().getCalle()+ ", "+ desaparicion.getLugar().getLocalidad() + ", " + desaparicion.getLugar().getProvincia() + ", España");
        desaparicion.getLugar().setLatitud(coordenadas.get("lat"));
        desaparicion.getLugar().setLongitud(coordenadas.get("lon"));
        desaparicion.getUsuario().getDesaparicionCreada().add(desaparicion);
        Set<Foto> listaFotos = new HashSet<>();
        for (MultipartFile f : files){
            Foto foto = new Foto();
            foto.setUrl(cloudinaryService.uploadImage(f));
            if (files.indexOf(f) == 0){
                foto.setEsCara(true);
            }else {
                foto.setEsCara(false);
            }
            listaFotos.add(foto);

        }
        desaparicion.getPersona().setFotos(listaFotos);
        desaparicion.setEliminada(false);
        desaparicionRepositorio.save(desaparicion);
        return desaparicion;
    }

    /**
     * Metodo que a partir de todas las desapariciones, devuelve una lista con una
     * dto preparada paara mostrarla en la página mostrar mas
     * @return List<DesaparionMostrarMasDTO>
     */

    public List<DesaparionMostrarMasDTO> mostrarMas(){
        List<Desaparicion> desapariciones = desaparicionRepositorio.findAllByEliminadaIsFalse();
        List<DesaparionMostrarMasDTO> devolucion = new ArrayList<>();
        for (Desaparicion desaparicion : desapariciones){
            List<String> fotos = new ArrayList<>();
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
        List<Desaparicion> desapariciones = desaparicionRepositorio.findTop10ByEliminadaIsFalseAndEstadoOrderByFechaDesc(ESTADO.DESAPARECIDO);
        return extraerPrincipalDTO(desapariciones);
    }

    /**
     * Metodo que a partir de todas las desapariciones, devuelve una lista con una
     * dto preparada paara mostrarla en la página principal o en seguimiento
     *
     * @return List<DesaparicionPrincipalDTO>
     */
    static List<DesaparicionPrincipalDTO> extraerPrincipalDTO(List<Desaparicion> desapariciones) {
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

    /**
     * Metodo para editar una desaparición
     * @return Desaparicion
     */

    public Desaparicion editarDesaparicion(Integer id, EditarDesaparicionDTO editarDesaparicionDTO) {
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElse(null);

        if (desaparicion == null) {
            return null;
        }

        // obtiene el lugar actual si ya existe si no crea uno nuevo
        Lugar lugar = desaparicion.getLugar() != null ? desaparicion.getLugar() : new Lugar();

        lugar.setProvincia(editarDesaparicionDTO.getLugarLatLongDTO().getProvincia());
        lugar.setLocalidad(editarDesaparicionDTO.getLugarLatLongDTO().getLocalidad());
        lugar.setCalle(editarDesaparicionDTO.getLugarLatLongDTO().getCalle());
        lugar.setLatitud(editarDesaparicionDTO.getLugarLatLongDTO().getLatitud());
        lugar.setLongitud(editarDesaparicionDTO.getLugarLatLongDTO().getLongitud());

        desaparicion.setEstado(editarDesaparicionDTO.getEstado());
        desaparicion.setLugar(lugar);

        return desaparicionRepositorio.save(desaparicion);
    }


    /**
     * Método para verificar una desaparición
     * @param autoridad
     * @return String mensaje
     */

    public String verificarDesaparicion(Autoridad autoridad, Integer id, boolean aprobada) {
        // busco la desaparicion por id
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Desaparición no encontrada"));

        // la autoridad decide si es falsa o no
        autoridad.verificarDesaparicion(desaparicion, aprobada);
        desaparicionRepositorio.save(desaparicion);

        // mensaje segun la decision
        return aprobada ? "Desaparición aprobada con éxito" : "Desaparición rechazada con éxito";
    }

    /**
     * Método que devuelve una lista de desapariciones pendientes (no verificadas)
     */

    public List<Desaparicion> getDesaparicionesPendientes() {
        return desaparicionRepositorio.desaparicionesPendientes();
    }
}

