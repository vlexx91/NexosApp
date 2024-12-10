package com.example.nexosapp.controladores;

import com.example.nexosapp.DTO.AvisoDTO;
import com.example.nexosapp.DTO.AvisoDTOAutoridadAdmin;
import com.example.nexosapp.DTO.CrearAvisoDTO;
import com.example.nexosapp.DTO.DesaparicionDTO;
import com.example.nexosapp.modelos.Aviso;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.servicios.AvisoServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Aviso", description = "Operaciones relacionadas con los avisos")
@RestController
@RequestMapping("/aviso")
@AllArgsConstructor
public class AvisoControlador {

    private AvisoServicio avisoSercicio;

    @Operation(summary = "Obtener todos los avisos")
    @GetMapping("/listar")
    public List<Aviso> getAllClientes(){
        return avisoSercicio.getAvisos();
    }

    @Operation(summary = "Obtener un aviso por id")
    @GetMapping()
    public Aviso getById(@RequestParam Integer id){
        return avisoSercicio.getAvisoId(id);
    }
    @Operation(summary = "Guardar un aviso")
    @PostMapping()
    public Aviso guardar(@RequestBody Aviso aviso){
        return avisoSercicio.guardar(aviso);
    }
    @Operation(summary = "eliminar un aviso")
    @DeleteMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        return avisoSercicio.eliminar(id);
    }

    @Operation(summary = "Obtener todas los avisos")
    @GetMapping("/mostrarAvisos")
    public List<AvisoDTO> getAll(){
        return avisoSercicio.getAll();
    }

    @Operation(summary = "Guardar un aviso")
    @PostMapping("/crearAviso")
    public ResponseEntity<String> guardarAviso(HttpServletRequest request, @RequestParam("aviso") String avisoJson, @RequestParam(value = "files",required = false) List<MultipartFile> files) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CrearAvisoDTO crearAvisoDTO = objectMapper.readValue(avisoJson, CrearAvisoDTO.class);
        return avisoSercicio.nuevoAviso(request,crearAvisoDTO, files);
    }

    @Operation(summary = "Listar avisos para el administrador")
    @GetMapping("/listarAvisosAdmin")
    public List<AvisoDTOAutoridadAdmin> listarAdminAvisos(){
        return avisoSercicio.listarAdminAvisos();
    }


}
