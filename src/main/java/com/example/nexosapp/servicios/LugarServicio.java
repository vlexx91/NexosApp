package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.MapaPrincipalDTO;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.modelos.Lugar;
import com.example.nexosapp.repositorios.LugarRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LugarServicio {


    @Autowired
    private LugarRepositorio lugarRepositorio;



    @Autowired
    @Lazy
    private DesaparicionServicio desaparicionServicio;

    /**
     * Obtener todos los lugares
     * @return
     */

    public List<Lugar> getLugares() {
        List<Lugar> lugar = lugarRepositorio.findAll();
        return lugar;
    }

    /**
     * Obtener lugares por ID
     */

    public Lugar getLugarId(Integer id) {
        return lugarRepositorio.findById(id).orElse(null);
    }

    /**
     * Guarda o edita un lugar
     */

    public Lugar guardar(Lugar lugar){
        return lugarRepositorio.save(lugar);
    }


    /**
     * Eliminar un lugar por ID
     */

    public String eliminaLugarId(Integer id) {
        String mensaje;
        Lugar lugar = lugarRepositorio.findById(id).orElse(null);

        if (lugar == null) {
            mensaje = "Ese lugar no existe";
            return mensaje;
        }

        try {
            lugarRepositorio.deleteById(id);
            Lugar lugartry = lugarRepositorio.findById(id).orElse(null);

            if (lugartry != null) {
                mensaje = "No se ha podido eliminar el lugar";
            } else {
                mensaje = "Lugar eliminado";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar el lugar";
        }

        return mensaje;
    }

    /**
     * Obtener los lugares de todas las desapariciones y sus datos
     * @return
     */

    public List<MapaPrincipalDTO>mapaPrincipal(){

        List<MapaPrincipalDTO> devolucion = new ArrayList<>();
        List<Desaparicion> listaLugares =desaparicionServicio.getDesapariciones();
        listaLugares.forEach(l->{
            MapaPrincipalDTO dto = new MapaPrincipalDTO(
              l.getId(),
              l.getPersona().getNombre(),
              l.getDescripcion(),
              l.getLugar().getLatitud(),
              l.getLugar().getLongitud()
            );
            devolucion.add(dto);
        });
        return devolucion;
    }
}
