package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.CivilDTO;
import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.servicios.CivilServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/civil")
@AllArgsConstructor
public class CivilControlador {

    private CivilServicio civilServicio;

    @GetMapping("/listar")
    public List<Civil> getAllCiviles(){
        return civilServicio.getCiviles();
    }

    @GetMapping()
    public Civil getById(@RequestParam Integer id){
        return civilServicio.getCivilId(id);
    }
    @PostMapping()
    public Civil guardar(@RequestBody Civil civil){
        return civilServicio.guardar(civil);
    }
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return civilServicio.eliminar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Civil> actualizarCivil(@PathVariable Integer id, @RequestBody CivilDTO civilDTO) {
        Civil civilActualizado = civilServicio.actualizarCivil(id, civilDTO);
        return ResponseEntity.ok(civilActualizado);
    }
}
