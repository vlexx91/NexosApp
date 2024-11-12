package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.CivilCrearDTO;
import com.example.nexosapp.DTO.CivilDTO;
import com.example.nexosapp.enumerados.ROL;
import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.repositorios.CivilRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CivilServicio {

    private CivilRepositorio civilRepositorio;
    private UsuarioService usuarioService;
    private PasswordEncoder passwordEncoder;

    /**
     * Guardar civil
     * @param civil
     * @return
     */
    public Civil guardar(Civil civil){
        return civilRepositorio.save(civil);
    }

    /**
     * Obtener todos los civiles
     * @return
     */
    public List<Civil>getCiviles(){
        return civilRepositorio.findAll();
    }

    /**
     * Obtener civil por id
     * @param id
     * @return
     */
    public Civil getCivilId(Integer id){
        return civilRepositorio.findById(id).orElse(null);
    }


    /**
     * eliminar civil por id
     * @param id
     * @return
     */
    public String eliminar(Integer id){
        String mensaje;
        Civil civil = getCivilId(id);

        if (civil == null){
            return "no existe ese civil";
        }
        try {
            civilRepositorio.deleteById(id);
            civil = getCivilId(id);
            if (civil!= null){
                mensaje = "No se ha podido eliminar el civil.";
            } else {
                mensaje = "Eliminado correctamente.";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar el civil.";
        }
        return mensaje;
    }
    public Civil actualizarCivil(Integer id, CivilDTO dto) {
        Civil civil = civilRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        civil.setNombre(dto.getNombre());
        civil.setApellido(dto.getApellido());
        civil.setTelefono(dto.getTelefono());

        return civilRepositorio.save(civil);
    }
    /**
     * Crear civil y a su vez el usuario asociado a este, valida si la contraseña coincide y si el nombre usuario ya existe
     * @param dto
     * @return
     */
    public Civil crearCivil(CivilCrearDTO dto){
        if (!dto.getUsuarioCrearDTO().getContrasenya().equals(dto.getUsuarioCrearDTO().getRepContrasenya())){
            return null;
        }
        if (usuarioService.buscarPorUsername(dto.getUsuarioCrearDTO().getUsuario())!=null){
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setUsuario(dto.getUsuarioCrearDTO().getUsuario());
        usuario.setContrasenya(passwordEncoder.encode(dto.getUsuarioCrearDTO().getContrasenya()));
        usuario.setEmail(dto.getUsuarioCrearDTO().getEmail());
        usuario.setRol(ROL.CIVIL);
        usuario.setVerificado(false);
        usuarioService.guardar(usuario);
        Civil civil = new Civil();
        civil.setNombre(dto.getNombre());
        civil.setApellido(dto.getApellido());
        civil.setDni(dto.getDni());
        civil.setTelefono(Integer.valueOf(dto.getTelefono()));
        civil.setUsuario(usuario);
        return civilRepositorio.save(civil);
    }

}
