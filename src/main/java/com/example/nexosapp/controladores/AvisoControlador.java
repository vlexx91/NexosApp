package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.AvisoDTO;
import com.example.nexosapp.DTO.CrearAvisoDTO;
import com.example.nexosapp.DTO.DesaparicionDTO;
import com.example.nexosapp.modelos.Aviso;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.servicios.AvisoServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/aviso")
@AllArgsConstructor
public class AvisoControlador {

    private AvisoServicio avisoSercicio;

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


    @GetMapping("/mostrarAvisos")
    public List<AvisoDTO> getAll(){
        return avisoSercicio.getAll();
    }

    @PostMapping("/crearAviso")
    public Aviso guardarAviso(@RequestParam("aviso") String avisoJson, @RequestParam(value = "files",required = false) List<MultipartFile> files) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CrearAvisoDTO crearAvisoDTO = objectMapper.readValue(avisoJson, CrearAvisoDTO.class);
        return avisoSercicio.nuevoAviso(crearAvisoDTO, files);
    }
}
