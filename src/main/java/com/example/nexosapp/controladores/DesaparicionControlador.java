package com.example.nexosapp.controladores;

import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.servicios.DesaparicionServicio;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desaparicion")
@AllArgsConstructor
public class DesaparicionControlador {

    private DesaparicionServicio desaparicionServicio;

    @GetMapping("/listar")
    public List<Desaparicion> getAllClientes(){
        return desaparicionServicio.getDesapariciones();
    }

    @GetMapping()
    public Desaparicion getById(@RequestParam Integer id){
        return desaparicionServicio.getDesaparicionId(id);
    }
    @PostMapping()
    public Desaparicion guardar(@RequestBody Desaparicion desaparicion){
        return desaparicionServicio.guardar(desaparicion);
    }
    @DeleteMapping()
    public String eliminar(@RequestParam Integer id) {
        return desaparicionServicio.eliminar(id);
    }
}
