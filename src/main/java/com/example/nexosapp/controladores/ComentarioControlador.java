package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.ComentarioDTO;
import com.example.nexosapp.DTO.ComentarioListarDTO;
import com.example.nexosapp.DTO.DenunciaComentarioDTO;
import com.example.nexosapp.modelos.Comentario;
import com.example.nexosapp.servicios.ComentarioServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Tag(name = "Comentario", description = "Operaciones relacionadas con los comentarios")
@RestController
@RequestMapping("/comentario")
public class ComentarioControlador {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Operation(summary = "Obtener todos los comentarios")
    @GetMapping("/listar")
    public List<Comentario> getAllComentarios(){
        List<Comentario> comentarios = comentarioServicio.getComentarios();

        return comentarios;
    }

    @Operation(summary = "Obtener un comentario por id")
    @GetMapping()
    public Comentario getById(@RequestParam Integer id){
        Comentario comentario = comentarioServicio.getComentario(id);
        return comentario;
    }

    @Operation(summary = "Guardar un comentario")
    @PostMapping()
    public Comentario guardar(@RequestBody Comentario comentario){
        Comentario comentarioNuevo = comentarioServicio.guardar(comentario);
        return comentarioNuevo;
    }

    @Operation(summary = "eliminar un comentario")
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestParam Integer id){
        return comentarioServicio.eliminar(id);
    }

    @Operation(summary = "Crear un comentario")
    @PostMapping("/crear")
    public ResponseEntity<String> crearComentario(@RequestParam("comentario") String comentarioJson, @RequestParam(value = "files",required = false) List<MultipartFile> files) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ComentarioDTO comentarioDTO = objectMapper.readValue(comentarioJson, ComentarioDTO.class);
        return comentarioServicio.crearComentario(comentarioDTO, files);
    }
    @Operation(summary = "Trae todos los comentarios de una desaparicion")
    @GetMapping("/desaparicion/{id}")
    public ResponseEntity<List<ComentarioListarDTO>> obtenerComentariosPorDesaparicion(@PathVariable Integer id) {
        List<ComentarioListarDTO> comentarios = comentarioServicio.obtenerComentariosPorDesaparicionId(id);
        return ResponseEntity.ok(comentarios);
    }

    @Operation(summary = "Denunciar un comentario")
    @PostMapping("/denunciar")
    public ResponseEntity<String> denuncia(@RequestBody DenunciaComentarioDTO denunciaComentarioDTO) {
        return comentarioServicio.denunciaComentario(denunciaComentarioDTO);
    }


}
