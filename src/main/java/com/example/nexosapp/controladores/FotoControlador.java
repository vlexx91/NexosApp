package com.example.nexosapp.controladores;

import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.servicios.FotoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Tag(name = "Foto", description = "Operaciones relacionadas con las fotos")
@RestController
@RequestMapping("/foto")
public class FotoControlador {

    @Autowired
    private FotoServicio fotoServicio;

    @Operation(summary = "Obtener todas las fotos")
    @GetMapping("/listar")
    public List<Foto> getAllBebidas(){
        List<Foto> fotos = fotoServicio.getFotos();

        return fotos;
    }

    @Operation(summary = "Obtener una foto por id")
    @GetMapping()
    public Foto getById(@RequestParam Integer id){
        Foto foto = fotoServicio.getFoto(id);
        return foto;
    }

    @Operation(summary = "Guardar una foto")
    @PostMapping("/crear")
    public Foto guardar(@RequestParam("file") MultipartFile file) throws IOException {
        Foto fotoNueva = fotoServicio.guardar(file);
        return fotoNueva;
    }

    @Operation(summary = "Eliminar una foto")
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id){
        return fotoServicio.eliminar(id);
    }

}
