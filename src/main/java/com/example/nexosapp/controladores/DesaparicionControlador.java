package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.*;
import com.example.nexosapp.modelos.Autoridad;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.repositorios.AutoridadRepositorio;
import com.example.nexosapp.repositorios.DesaparicionRepositorio;
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
    public ResponseEntity<String> guardarDesaparicion(
            HttpServletRequest request,
            @RequestParam("desaparicion") String desaparicionJson,
            @RequestParam("files") List<MultipartFile> files) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            DesaparicionDTO desaparicionDTO = objectMapper.readValue(desaparicionJson, DesaparicionDTO.class);


            desaparicionServicio.guardarDesaparicion(request, desaparicionDTO, files);


            return ResponseEntity.status(HttpStatus.CREATED).body("Desaparición creada con éxito");
        } catch (IOException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al procesar los datos de la desaparición: " + e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al guardar la desaparición: " + e.getMessage());
        }
    }


    @DeleteMapping()
    public ResponseEntity<String> eliminar(@RequestParam Integer id) {
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

    @PostMapping("/editarAutoridadDesaparicion")
    public ResponseEntity<String> editarDesaparicionAutoridad(@RequestParam Integer id, @RequestBody EditarDesaparicionDTO editarDesaparicionDTO){
        return desaparicionServicio.editarDesaparicion(id, editarDesaparicionDTO);
    }

    @GetMapping("/getDesaparicionEditar")
    public EditarDesaparicionDTO getDesaparicionEditar(@RequestParam Integer id){
        return desaparicionServicio.getEditarDesaparicionDTO(id);
    }

    @PutMapping("/aprobar")
    public ResponseEntity<String> verificarDesaparicion(@RequestParam Integer id) {
        return desaparicionServicio.verificarDesaparicion(id);
    }

    @PutMapping("/eliminar")
    public ResponseEntity<String> eliminarDesaparicion(@RequestParam Integer id){
        return desaparicionServicio.eliminarDesaparicion(id);
    }

    @GetMapping("/pendientes")
    public List<Desaparicion> getDesaparicionesPendientes(){
        return desaparicionServicio.getDesaparicionesPendientes();
    }

    @GetMapping()
    public DesaparicionIndividualDTO getDesaparicionId(@RequestParam Integer id){
        return desaparicionServicio.getDesaparicion(id);
    }

    @GetMapping("/NoAprobadas")
    public List<DesaparicionSinVerificarDTO> getDesaparicionesNoAprobadas(){
        return desaparicionServicio.getSinAprobar();
    }

    @GetMapping("/eliminadas")
    public List<DesaparicionSinVerificarDTO> getDesaparicionesEliminadas(){
        return desaparicionServicio.listaEliminadas();
    }

    @PostMapping("/recuperar")
    public ResponseEntity<String> recuperarDesaparicion(@RequestParam Integer id){
        return desaparicionServicio.recuperarEliminacion(id);
    }


}



