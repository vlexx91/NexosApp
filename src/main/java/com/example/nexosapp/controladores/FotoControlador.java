package com.example.nexosapp.controladores;

import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.servicios.FotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foto")
public class FotoControlador {

    @Autowired
    private FotoServicio fotoServicio;

    @GetMapping("/listar")
    public List<Foto> getAllBebidas(){
        List<Foto> fotos = fotoServicio.getFotos();

        return fotos;
    }

    @GetMapping()
    public Foto getById(@RequestParam Integer id){
        Foto foto = fotoServicio.getFoto(id);
        return foto;
    }

    @PostMapping()
    public Foto guardar(@RequestBody Foto foto){
        Foto fotoNueva = fotoServicio.guardar(foto);
        return fotoNueva;
    }

    @DeleteMapping()
    public String eliminar(@RequestParam Integer id){
        return fotoServicio.eliminar(id);
    }

}
