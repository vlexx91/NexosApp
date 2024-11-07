package com.example.nexosapp.controladores;

import com.example.nexosapp.modelos.Comentario;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.servicios.ComentarioServicio;
import com.example.nexosapp.servicios.FotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
