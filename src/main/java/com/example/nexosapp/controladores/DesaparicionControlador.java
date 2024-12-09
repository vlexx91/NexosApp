package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.*;
import com.example.nexosapp.enumerados.ESTADO;
import com.example.nexosapp.enumerados.Sexo;
import com.example.nexosapp.modelos.*;
import com.example.nexosapp.repositorios.AutoridadRepositorio;
import com.example.nexosapp.repositorios.DesaparicionRepositorio;
import com.example.nexosapp.servicios.AutoridadServicio;
import com.example.nexosapp.servicios.DesaparicionServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
@Tag(name = "Desaparicion", description = "Operaciones relacionadas con las desapariciones")
@RestController
@RequestMapping("/desaparicion")
@AllArgsConstructor
public class DesaparicionControlador {

    private DesaparicionServicio desaparicionServicio;
    private AutoridadServicio autoridadServicio;

    @Operation(summary = "Obtener todas las desapariciones")
    @GetMapping("/listar")
    public List<Desaparicion> getAllDesapariciones(){
        return desaparicionServicio.getDesapariciones();
    }

    @Operation(summary = "Obtener una desaparicion por id")
    @GetMapping("/getById")
    public Desaparicion getById(@RequestParam Integer id){
        return desaparicionServicio.getDesaparicionId(id);
    }
    @Operation(summary = "Guardar una desaparicion")
    @PostMapping()
    public Desaparicion guardar(@RequestBody Desaparicion desaparicion){
        return desaparicionServicio.guardar(desaparicion);
    }
    @Operation(summary = "Guardar una desaparicion, incluyendo archivos y manejo de errores")
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

    @Operation(summary = "eliminar una desaparicion")
    @DeleteMapping()
    public ResponseEntity<String> eliminar(@RequestParam Integer id) {
        return desaparicionServicio.eliminar(id);
    }

    @Operation(summary = "Lista de desapariciones para la página mostrar más")
    @GetMapping("/mostrarMas")
    public List<DesaparionMostrarMasDTO> mostrarMas(){
        return desaparicionServicio.mostrarMas();
    }

    @Operation(summary = "Lista de desapariciones para la página principal")
    @GetMapping("/principal")
    public List<DesaparicionPrincipalDTO> principal(){
        return desaparicionServicio.paginaPrincipal();
    }
    @Operation(summary = "Edición de una desaparición")
    @PostMapping("/editarAutoridadDesaparicion")
    public ResponseEntity<String> editarDesaparicionAutoridad(@RequestParam Integer id, @RequestBody EditarDesaparicionDTO editarDesaparicionDTO){
        return desaparicionServicio.editarDesaparicion(id, editarDesaparicionDTO);
    }
    @Operation(summary = "Obtener una desaparición para editar")
    @GetMapping("/getDesaparicionEditar")
    public EditarDesaparicionDTO getDesaparicionEditar(@RequestParam Integer id){
        return desaparicionServicio.getEditarDesaparicionDTO(id);
    }
    @Operation(summary = "Obtener una desaparición para editar por parte de una autoridad")
    @GetMapping("/getDesaparicionEditarAutoridad")
    public DesaparicionEditarAutoridadDTO getDesaparicionEditarAutoridad(@RequestParam Integer id){
        return desaparicionServicio.getDesaparicionEditarAutoridadDTO(id);
    }

    @Operation(summary = "Verificar una desaparición")
    @PutMapping("/aprobar")
    public ResponseEntity<String> verificarDesaparicion(@RequestParam Integer id) {
        return desaparicionServicio.verificarDesaparicion(id);
    }

    @Operation(summary = "Eliminar una desaparición")
    @PutMapping("/eliminar")
    public ResponseEntity<String> eliminarDesaparicion(@RequestParam Integer id){
        return desaparicionServicio.eliminarDesaparicion(id);
    }

    @Operation(summary = "Obtener desapariciones pendientes")
    @GetMapping("/pendientes")
    public List<Desaparicion> getDesaparicionesPendientes(){
        return desaparicionServicio.getDesaparicionesPendientes();
    }

    @Operation(summary = "Obtener desaparicion por id")
    @GetMapping()
    public DesaparicionIndividualDTO getDesaparicionId(@RequestParam Integer id){
        return desaparicionServicio.getDesaparicion(id);
    }

    @Operation(summary = "Obtener desapariciones no aprobadas")
    @GetMapping("/NoAprobadas")
    public List<DesaparicionSinVerificarDTO> getDesaparicionesNoAprobadas(){
        return desaparicionServicio.getSinAprobar();
    }
//    @GetMapping("/filtrar")
//    public ResponseEntity<?> buscarPorCriterios(
//            @RequestParam(required = false) String estado,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
//            @RequestParam(required = false) String nombre,
//            @RequestParam(required = false, defaultValue = "false") boolean ultimas24) {
//
//        List<Desaparicion> desapariciones = desaparicionServicio.buscarPorCriterios(fecha, estado, nombre, ultimas24);
//
//        if (desapariciones.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("No se encontraron desapariciones con los criterios proporcionados.");
//        }
//
//        // Transformar los resultados para la respuesta
//        List<Map<String, Object>> resultados = desapariciones.stream().map(desaparicion -> {
//            Map<String, Object> result = new HashMap<>();
//            result.put("id", desaparicion.getId());
//            result.put("nombre", desaparicion.getPersona().getNombre());
//            result.put("apellidos", desaparicion.getPersona().getApellido());
//            result.put("fecha", desaparicion.getFecha());
//            result.put("foto", desaparicion.getPersona().getFotos()
//                    .stream()
//                    .findFirst()
//                    .map(Foto::getUrl)
//                    .orElse("default.jpg"));
//            return result;
//        }).toList();
//
//        return ResponseEntity.ok(resultados);
//    }
@Operation(summary = "Buscar desapariciones por fecha, estado y nombre")
@GetMapping("/filtrar")
public ResponseEntity<?> buscarPorFechaEstadoYNombre(
        @RequestParam(required = false) String estado,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
        @RequestParam(required = false) String nombre) {

    if (estado == null && nombre == null) {
        return ResponseEntity.badRequest().body("Debe proporcionar al menos un criterio de búsqueda.");
    }

    List<Desaparicion> desapariciones = desaparicionServicio.buscarPorFechaEstadoYNombre(fecha, estado, nombre);

    if (desapariciones.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron resultados.");
    }

    List<Map<String, Object>> resultados = desapariciones.stream().map(desaparicion -> {
        Map<String, Object> result = new HashMap<>();
        result.put("id", desaparicion.getId());
        result.put("nombre", desaparicion.getPersona().getNombre());
        result.put("apellidos", desaparicion.getPersona().getApellido());
        result.put("fecha", desaparicion.getFecha());
        result.put("foto", desaparicion.getPersona().getFotos()
                .stream()
                .findFirst()
                .map(Foto::getUrl)
                .orElse("default.jpg"));
        return result;
    }).toList();

    return ResponseEntity.ok(resultados);
    }

    @Operation(summary = "Obtener desapariciones eliminadas")
    @GetMapping("/eliminadas")
    public List<DesaparicionSinVerificarDTO> getDesaparicionesEliminadas(){
        return desaparicionServicio.listaEliminadas();
    }
    @Operation(summary = "Recuperar una desaparición eliminada")
    @PostMapping("/recuperar")
    public ResponseEntity<String> recuperarDesaparicion(@RequestParam Integer id){
        return desaparicionServicio.recuperarEliminacion(id);
    }
    @Operation(summary = "Obtener desapariciones para su gestión")
    @GetMapping("/desaparicionesGestion")
    public List<DesaparicionGestionDTO> getDesaparicionesGestion(){
        return desaparicionServicio.getDesaparicionesGestion();
    }
    @Operation(summary = "Edición de una desaparición por parte de una autoridad")
    @PutMapping("/editarDesaparicionGestion")
    public ResponseEntity<String> editarDesaparicionGestion(
            @RequestParam("id") Integer id,
            @RequestParam("desaparicion") String desaparicionJson,
            @RequestParam(value = "files", required = false) List<MultipartFile> files) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DesaparicionEditarAutoridadDTO desaparicionEditarAutoridadDTO = objectMapper.readValue(desaparicionJson, DesaparicionEditarAutoridadDTO.class);

            List<MultipartFile> fileList = files != null ? files : Collections.emptyList();

            ResponseEntity<String> result = desaparicionServicio.editarDesaparicionGestion(id, desaparicionEditarAutoridadDTO, fileList);

            return ResponseEntity.status(HttpStatus.OK).body(result.getBody());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al procesar los datos de la desaparición: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al guardar la desaparición: " + e.getMessage());
        }
    }

}
