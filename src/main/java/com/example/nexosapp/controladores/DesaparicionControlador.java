package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.*;
import com.example.nexosapp.modelos.Autoridad;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.repositorios.AutoridadRepositorio;
import com.example.nexosapp.servicios.AutoridadServicio;
import com.example.nexosapp.servicios.DesaparicionServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/desaparicion")
@AllArgsConstructor
public class DesaparicionControlador {

    private DesaparicionServicio desaparicionServicio;
    private AutoridadServicio autoridadServicio;

    @GetMapping("/listar")
    public List<Desaparicion> getAllDesapariciones(){
        return desaparicionServicio.getDesapariciones();
    }

    @GetMapping("/getById")
    public Desaparicion getById(@RequestParam Integer id){
        return desaparicionServicio.getDesaparicionId(id);
    }
    @PostMapping()
    public Desaparicion guardar(@RequestBody Desaparicion desaparicion){
        return desaparicionServicio.guardar(desaparicion);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Map<String, String>> guardarDesaparicion(
            HttpServletRequest request,
            @RequestParam("desaparicion") String desaparicionJson,
            @RequestParam("files") List<MultipartFile> files) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DesaparicionDTO desaparicionDTO = objectMapper.readValue(desaparicionJson, DesaparicionDTO.class);


            desaparicionServicio.guardarDesaparicion(request,desaparicionDTO, files);

            // Devuelve un JSON con el mensaje
            Map<String, String> response = new HashMap<>();
            response.put("message", "Desaparición creada");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IOException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error al procesar los datos de la desaparición: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error al guardar la desaparición: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
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

    @PostMapping("/editarAutoridad")
    public Desaparicion editarDesaparicionAutoridad(@RequestParam Integer id, @RequestBody EditarDesaparicionDTO editarDesaparicionDTO){
        return desaparicionServicio.editarDesaparicion(id, editarDesaparicionDTO);
    }

    @PostMapping("/verificar")
    public String verificarDesaparicion(@RequestBody Autoridad autoridad, @RequestParam Integer id, @RequestParam boolean aprobada) {
        try {
            return desaparicionServicio.verificarDesaparicion(autoridad, id, aprobada);
        } catch (IllegalArgumentException | SecurityException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/pendientes")
    public List<Desaparicion> getDesaparicionesPendientes(){
        return desaparicionServicio.getDesaparicionesPendientes();
    }

    @GetMapping()
    public DesaparicionSinVerificarDTO getDesaparicionId(@RequestParam Integer id){
        return desaparicionServicio.getDesaparicion(id);
    }


}



