package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.LugarLatLongDTO;
import com.example.nexosapp.DTO.MapaPrincipalDTO;
import com.example.nexosapp.modelos.Lugar;
import com.example.nexosapp.servicios.LugarServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Lugar", description = "Operaciones relacionadas con los lugares")
@RestController
@RequestMapping("/lugar")
@AllArgsConstructor
public class LugarControlador {

    private LugarServicio lugarServicio;

    /**
     * obtiene todos los lugares
     * @return
     */
    @Operation(summary = "Obtener todos los lugares")
    @GetMapping("/lista")
    public List<Lugar> getAllLugares(){
        return lugarServicio.getLugares();
    }

    /**
     * obtiene lugar por id
     * @param id
     * @return
     */
    @Operation(summary = "Obtener un lugar por id")
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
    @Operation(summary = "Guardar un lugar")
    @PostMapping("/guardar")
    public Lugar guardar(@RequestBody Lugar lugar){
        lugar = lugarServicio.guardar(lugar);
        return lugar;
    }

    @Operation(summary = "Eliminar un lugar")
    @DeleteMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id){
        try {
            lugarServicio.eliminaLugarId(id);
            return "lugar eliminado";
        } catch (Exception e){
            return "imposible eliminar lugar";
        }
    }
    @Operation(summary = "Obtener todos los lugares para el mapa principal")
    @GetMapping("/mapaPrincipal")
    public List<MapaPrincipalDTO> getMapaPrincipal(){
        return lugarServicio.mapaPrincipal();
    }

    @Operation(summary = "Obtener un lugar por id para el mapa de una desaparición")
    @GetMapping("/mapa")
    public LugarLatLongDTO getLugarDesaparicion(@RequestParam("id") Integer id){
        return lugarServicio.getLugarDesaparicion(id);
    }


}
