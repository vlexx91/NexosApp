package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.LugarLatLongDTO;
import com.example.nexosapp.DTO.MapaPrincipalDTO;
import com.example.nexosapp.modelos.Lugar;
import com.example.nexosapp.servicios.LugarServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lugar")
@AllArgsConstructor
public class LugarControlador {

    private LugarServicio lugarServicio;

    /**
     * obtiene todos los lugares
     * @return
     */

    @GetMapping("/lista")
    public List<Lugar> getAllLugares(){
        return lugarServicio.getLugares();
    }

    /**
     * obtiene lugar por id
     * @param id
     * @return
     */

    @GetMapping("/obtener/{id}")
    public Lugar getLugarId(@PathVariable Integer id){
        Lugar lugar = lugarServicio.getLugarId(id);
        return lugar;
    }

    /**
     * guarda o edita un lugar
     * @param lugar
     * @return
     */

    @PostMapping("/guardar")
    public Lugar guardar(@RequestBody Lugar lugar){
        lugar = lugarServicio.guardar(lugar);
        return lugar;
    }


    @DeleteMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id){
        try {
            lugarServicio.eliminaLugarId(id);
            return "lugar eliminado";
        } catch (Exception e){
            return "imposible eliminar lugar";
        }
    }

    @GetMapping("/mapaPrincipal")
    public List<MapaPrincipalDTO> getMapaPrincipal(){
        return lugarServicio.mapaPrincipal();
    }

    @GetMapping("/mapa")
    public LugarLatLongDTO getLugarDesaparicion(@RequestParam("id") Integer id){
        return lugarServicio.getLugarDesaparicion(id);
    }


}
