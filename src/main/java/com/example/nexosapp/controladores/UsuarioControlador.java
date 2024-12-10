package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.DesaparicionPrincipalDTO;
import com.example.nexosapp.DTO.UsuarioAdminListaDTO;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.seguridad.JWTservice;
import com.example.nexosapp.seguridad.filter.JWTFilter;
import com.example.nexosapp.servicios.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Tag(name = "Usuario", description = "Operaciones relacionadas con los usuarios")
@RestController
@RequestMapping("/usuario")
@AllArgsConstructor

public class UsuarioControlador {

    private UsuarioService usuarioService;

    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping("/getAll")
    public List<Usuario> getAll(){

        return usuarioService.getUsuarios();
    }

    @Operation(summary = "Obtener un usuario por id")
    @GetMapping
    public Usuario getById(@RequestParam Integer id){
        return usuarioService.getUsuarioId(id);
    }

    @Operation(summary = "Guardar un usuario")
    @PostMapping("/guardar")
    public Usuario guardar(@RequestBody Usuario usuario){
        return usuarioService.guardar(usuario);
    }
//    @PostMapping("/registro")
//    public String guardar(@RequestBody UsuarioDTO usuarioDTO){
//        return usuarioService.guardarUsario(usuarioDTO);
//    }

    @Operation(summary = "eliminar un usuario de tipo civil")
    @DeleteMapping("/eliminar/civil")
    public String eliminar(@RequestParam Integer id){
        return usuarioService.eliminaUsuarioIdCivil(id);
    }

    @Operation(summary = "Añade una desaoaricion a la lista de seguimiento de un usuario")
    @PostMapping("/seguimiento/anyadir")
    public ResponseEntity<Void> anyadirSeguimiento(HttpServletRequest request, @RequestParam Integer idDesaparicion) {
        usuarioService.anyadirSeguimiento(request, idDesaparicion);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Eliminar una desaparicion de la lista de seguimiento de un usuario")
    @DeleteMapping("/seguimiento/eliminar")
    public ResponseEntity<Void> eliminarSeguimiento(HttpServletRequest request, @RequestParam Integer idDesaparicion) {
        usuarioService.eliminarSeguimiento(request, idDesaparicion);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Obtener la lista de usuarios para el administrador")
    @GetMapping("/listaUsuarios")
    public List<UsuarioAdminListaDTO> listaUsuarios(){
        return usuarioService.usuarioAdminLista();
    }

    @Operation(summary = "Obtener el rol del usuario")
    @GetMapping("/rol/")
    public String getRol(HttpServletRequest request) {
        return usuarioService.getRol(request);
    }



    @Operation(summary = "Eliminar un usuario por id")
    @DeleteMapping("/eliminar")
    public ResponseEntity<Map<String, String>> eliminaUsuarioId(@RequestParam Integer id){
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", usuarioService.eliminaUsuarioIdCivil(id));
        return ResponseEntity.ok(respuesta);
    }
    @Operation(summary = "Verifica a un usuario por id")
    @PutMapping("/verifica")
    public ResponseEntity<Map<String, String>> verificaUsuarioId(@RequestParam Integer id){
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", usuarioService.verificaUsuarioId(id));
        return ResponseEntity.ok(respuesta);
    }
}
