package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.CivilConfirmarDTO;
import com.example.nexosapp.DTO.CivilDTO;
import com.example.nexosapp.DTO.DesaparicionListaDTO;
import com.example.nexosapp.DTO.UsuarioMenuDTO;
import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.servicios.CivilServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/civil")
@AllArgsConstructor
public class CivilControlador {

    private CivilServicio civilServicio;

    @GetMapping("/listar")
    public List<Civil> getAllCiviles(){
        return civilServicio.getCiviles();
    }

    @GetMapping()
    public Civil getById(@RequestParam Integer id){
        return civilServicio.getCivilId(id);
    }
    @PostMapping()
    public Civil guardar(@RequestBody Civil civil){
        return civilServicio.guardar(civil);
    }
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return civilServicio.eliminar(id);
    }

    @PutMapping()
    public ResponseEntity<Map<String, String>> actualizarCivil(HttpServletRequest request, @RequestBody CivilDTO civilDTO) {
        civilServicio.actualizarCivil(request, civilDTO);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Civil actualizado correctamente");
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/editar")
    public CivilDTO editar(HttpServletRequest request){
        return civilServicio.getCivilEditar(request);
    }

    @GetMapping("/listaDesapariciones")
    public List<DesaparicionListaDTO> getDesaparicionesLista(HttpServletRequest request){
        return civilServicio.misDesapariciones(request);
    }

    @GetMapping("/seguimiento")
    public List<DesaparicionListaDTO> getSeguimiento(HttpServletRequest request){
        return civilServicio.seguimiento(request);
    }

    @GetMapping("/menu")
    public UsuarioMenuDTO getMenu(HttpServletRequest request){
        return civilServicio.menUsuario(request);
    }

    @PostMapping("/verificar")
    public ResponseEntity verificar(@RequestParam Integer id) throws MessagingException {
        return civilServicio.verificarUsuario(id);
    }
    @GetMapping("/listaVerificar")
    public List<CivilConfirmarDTO> listaVerificar(){
        return civilServicio.getCivilesSinConfirmar();
    }
}
