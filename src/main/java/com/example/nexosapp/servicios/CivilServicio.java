package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.*;
import com.example.nexosapp.enumerados.ROL;
import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.repositorios.CivilRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * Funcion que edita los datos de un usuario tipo civil
     * @param id
     * @param dto
     * @return
     */

    public Civil actualizarCivil(Integer id, CivilDTO dto) {
        Civil civil = civilRepositorio.findTopByUsuarioId(id);

        civil.setNombre(dto.getNombre());
        civil.setApellido(dto.getApellido());
        civil.setTelefono(dto.getTelefono());

        return civilRepositorio.save(civil);
    }

    /**
     * Funcion que obtiene los datos de un civil para editar
     * @param id
     * @return
     */

    public CivilDTO getCivilEditar(Integer id){
        Civil civil = civilRepositorio.findTopByUsuarioId(id);
        CivilDTO dto = new CivilDTO(civil.getDni(), civil.getNombre(), civil.getApellido(), civil.getTelefono());
        return dto;
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

    /**
     * Método que devuelve una lista de desapariciones creadas por el usuario
     * @param id
     * @return
     */
    public List<DesaparicionListaDTO> misDesapariciones(Integer id){
        Usuario usuario = usuarioService.getUsuarioId(id);
        Set<Desaparicion> desapariciones = usuario.getDesaparicionCreada();
        desapariciones = desapariciones.stream().filter(d->!d.getAprobada()).collect(Collectors.toSet());
        List<DesaparicionListaDTO> devolucion = new ArrayList<>();
        desapariciones.forEach(d->{
            DesaparicionListaDTO dto = new DesaparicionListaDTO();
            dto.setId(d.getId());
            dto.setComplexion(d.getPersona().getComplexion().toString());
            dto.setNombre(d.getPersona().getNombre());
            dto.setApellidos(d.getPersona().getApellido());
            dto.setFechaNacimiento(d.getPersona().getFechaNacimiento().toString());
            dto.setDescripcion(d.getDescripcion());
            dto.setFecha(d.getFecha().toString());
            dto.setSexo(d.getPersona().getSexo().toString());
            dto.setEstado(d.getEstado().toString());
            dto.setDireccion(d.getLugar().getCalle() + ", " + d.getLugar().getLocalidad() + ", " + d.getLugar().getProvincia());
            dto.setUrlFotoCara(d.getPersona().getFotos().stream().filter(Foto::getEsCara).findFirst().get().getUrl());
            devolucion.add(dto);
        });
        return devolucion;
    }

    public UsuarioMenuDTO menUsuario(Integer id){
        Civil civil = civilRepositorio.findTopByUsuarioId(id);
        Usuario usuario = civil.getUsuario();
        UsuarioMenuDTO dto = new UsuarioMenuDTO(usuario.getUsuario(), usuario.getEmail());
        return dto;
    }

}
