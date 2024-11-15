package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.DesaparicionPrincipalDTO;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.servicios.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String anyadirSeguimiento(@RequestParam Integer idUsuario, @RequestParam Integer idDesaparicion){
        return usuarioService.anyadirSeguimiento(idUsuario, idDesaparicion);
    }

    @GetMapping("/seguimiento")
    public List<DesaparicionPrincipalDTO> getSeguimiento(@RequestParam Integer id){
        return usuarioService.desaparicionesSeguidas(id);
    }
}
