package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.CivilConfirmarDTO;
import com.example.nexosapp.DTO.CivilDTO;
import com.example.nexosapp.DTO.DesaparicionListaDTO;
import com.example.nexosapp.DTO.UsuarioMenuDTO;
import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.servicios.CivilServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Civil", description = "Operaciones relacionadas con los civiles")
@RestController
@RequestMapping("/civil")
@AllArgsConstructor
public class CivilControlador {

    private CivilServicio civilServicio;

    @Operation(summary = "Obtener todos los civiles")
    @GetMapping("/listar")
    public List<Civil> getAllCiviles(){
        return civilServicio.getCiviles();
    }

    @Operation(summary = "Obtener un civil por id")
    @GetMapping()
    public Civil getById(@RequestParam Integer id){
        return civilServicio.getCivilId(id);
    }
    @Operation(summary = "Guardar un civil")
    @PostMapping()
    public Civil guardar(@RequestBody Civil civil){
        return civilServicio.guardar(civil);
    }
    @Operation(summary = "eliminar un civil")
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return civilServicio.eliminar(id);
    }

    @Operation(summary = "Actualizar un civil")
    @PutMapping()
    public ResponseEntity<Map<String, String>> actualizarCivil(HttpServletRequest request, @RequestBody CivilDTO civilDTO) {
        civilServicio.actualizarCivil(request, civilDTO);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Civil actualizado correctamente");
        return ResponseEntity.ok(respuesta);
    }

    @Operation(summary = "Obtiene los datos de un civil para editar")
    @GetMapping("/editar")
    public CivilDTO editar(HttpServletRequest request){
        return civilServicio.getCivilEditar(request);
    }

    @Operation(summary = "Obtener todas las desapariciones")
    @GetMapping("/listaDesapariciones")
    public List<DesaparicionListaDTO> getDesaparicionesLista(HttpServletRequest request){
        return civilServicio.misDesapariciones(request);
    }

    @Operation(summary = "Obtener todas las desapariciones que se siguen")
    @GetMapping("/seguimiento")
    public List<DesaparicionListaDTO> getSeguimiento(HttpServletRequest request){
        return civilServicio.seguimiento(request);
    }

    @Operation(summary = "Obtiene todos los datos del usuario para el menu")
    @GetMapping("/menu")
    public UsuarioMenuDTO getMenu(HttpServletRequest request){
        return civilServicio.menUsuario(request);
    }

    @Operation(summary = "Verifica un usuario")
    @PostMapping("/verificar")
    public ResponseEntity verificar(@RequestParam Integer id) throws MessagingException {
        return civilServicio.verificarUsuario(id);
    }
    @Operation(summary = "Lista los civiles sin verificar")
    @GetMapping("/listaVerificar")
    public List<CivilConfirmarDTO> listaVerificar(){
        return civilServicio.getCivilesSinConfirmar();
    }
}
