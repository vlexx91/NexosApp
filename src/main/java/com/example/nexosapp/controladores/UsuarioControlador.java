package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.DesaparicionPrincipalDTO;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.seguridad.JWTservice;
import com.example.nexosapp.seguridad.filter.JWTFilter;
import com.example.nexosapp.servicios.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor

public class UsuarioControlador {

    private UsuarioService usuarioService;


    @GetMapping("/getAll")
    public List<Usuario> getAll(){

        return usuarioService.getUsuarios();
    }

    @GetMapping
    public Usuario getById(@RequestParam Integer id){
        return usuarioService.getUsuarioId(id);
    }

    @PostMapping("/guardar")
    public Usuario guardar(@RequestBody Usuario usuario){
        return usuarioService.guardar(usuario);
    }
//    @PostMapping("/registro")
//    public String guardar(@RequestBody UsuarioDTO usuarioDTO){
//        return usuarioService.guardarUsario(usuarioDTO);
//    }

    @DeleteMapping("/eliminar/civil")
    public String eliminar(@RequestParam Integer id){
        return usuarioService.eliminaUsuarioIdCivil(id);
    }

    @PostMapping("/seguimiento/anyadir")
    public ResponseEntity<Void> anyadirSeguimiento(HttpServletRequest request, @RequestParam Integer idDesaparicion) {
        usuarioService.anyadirSeguimiento(request, idDesaparicion);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/seguimiento/eliminar")
    public ResponseEntity<Void> eliminarSeguimiento(HttpServletRequest request, @RequestParam Integer idDesaparicion) {
        usuarioService.eliminarSeguimiento(request, idDesaparicion);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Map<String, String>> eliminaUsuarioId(@RequestParam Integer id){
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", usuarioService.eliminaUsuarioIdCivil(id));
        return ResponseEntity.ok(respuesta);
    }
    @PutMapping("/verifica")
    public ResponseEntity<Map<String, String>> verificaUsuarioId(@RequestParam Integer id){
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", usuarioService.verificaUsuarioId(id));
        return ResponseEntity.ok(respuesta);
    }
}
