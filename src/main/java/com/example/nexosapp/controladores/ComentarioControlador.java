package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.ComentarioDTO;
import com.example.nexosapp.DTO.ComentarioListarDTO;
import com.example.nexosapp.DTO.DesaparicionDTO;
import com.example.nexosapp.modelos.Comentario;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.servicios.ComentarioServicio;
import com.example.nexosapp.servicios.FotoServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @GetMapping("/listar")
    public List<Comentario> getAllComentarios(){
        List<Comentario> comentarios = comentarioServicio.getComentarios();

        return comentarios;
    }

    @GetMapping()
    public Comentario getById(@RequestParam Integer id){
        Comentario comentario = comentarioServicio.getComentario(id);
        return comentario;
    }

    @PostMapping()
    public Comentario guardar(@RequestBody Comentario comentario){
        Comentario comentarioNuevo = comentarioServicio.guardar(comentario);
        return comentarioNuevo;
    }

    @DeleteMapping()
    public String eliminar(@RequestParam Integer id){
        return comentarioServicio.eliminar(id);
    }

    @PostMapping("/crear")
    public Comentario crearComentario(@RequestParam("comentario") String comentarioJson, @RequestParam(value = "files",required = false) List<MultipartFile> files) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ComentarioDTO comentarioDTO = objectMapper.readValue(comentarioJson, ComentarioDTO.class);
        return comentarioServicio.crearComentario(comentarioDTO, files);
    }
    @GetMapping("/desaparicion/{id}")
    public ResponseEntity<List<ComentarioListarDTO>> obtenerComentariosPorDesaparicion(@PathVariable Integer id) {
        List<ComentarioListarDTO> comentarios = comentarioServicio.obtenerComentariosPorDesaparicionId(id);
        return ResponseEntity.ok(comentarios);
    }


}
