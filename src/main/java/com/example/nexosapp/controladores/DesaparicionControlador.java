package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.DesaparicionDTO;
import com.example.nexosapp.DTO.DesaparicionPrincipalDTO;
import com.example.nexosapp.DTO.DesaparionMostrarMasDTO;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.servicios.DesaparicionServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/desaparicion")
@AllArgsConstructor
public class DesaparicionControlador {

    private DesaparicionServicio desaparicionServicio;

    @GetMapping("/listar")
    public List<Desaparicion> getAllDesapariciones(){
        return desaparicionServicio.getDesapariciones();
    }

    @GetMapping()
    public Desaparicion getById(@RequestParam Integer id){
        return desaparicionServicio.getDesaparicionId(id);
    }
    @PostMapping()
    public Desaparicion guardar(@RequestBody Desaparicion desaparicion){
        return desaparicionServicio.guardar(desaparicion);
    }

    @PostMapping("/guardar")
    public Desaparicion guardarDesaparicion( @RequestParam("desaparicion") String desaparicionJson, @RequestParam("files") List<MultipartFile> files) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DesaparicionDTO desaparicionDTO = objectMapper.readValue(desaparicionJson, DesaparicionDTO.class);
        return desaparicionServicio.guardarDesaparicion(desaparicionDTO, files);
    }
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return desaparicionServicio.eliminar(id);
    }

    @GetMapping("/mostrarMas")
    public List<DesaparionMostrarMasDTO> mostrarMas(){
        return desaparicionServicio.mostrarMas();
    }

    @GetMapping("/principal")
    public List<DesaparicionPrincipalDTO> principal(){
        return desaparicionServicio.paginaPrincipal();
    }
}



