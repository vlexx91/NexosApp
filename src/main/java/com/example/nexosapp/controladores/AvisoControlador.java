package com.example.nexosapp.controladores;

import com.example.nexosapp.modelos.Aviso;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.servicios.AvisoSercicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aviso")
@AllArgsConstructor
public class AvisoControlador {

    private AvisoSercicio avisoSercicio;

    @GetMapping("/listar")
    public List<Aviso> getAllClientes(){
        return avisoSercicio.getAvisos();
    }

    @GetMapping()
    public Aviso getById(@RequestParam Integer id){
        return avisoSercicio.getAvisoId(id);
    }
    @PostMapping()
    public Aviso guardar(@RequestBody Aviso aviso){
        return avisoSercicio.guardar(aviso);
    }
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return avisoSercicio.eliminar(id);
    }
}
