package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.*;
import com.example.nexosapp.enumerados.ROL;
import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.modelos.Foto;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.recursos.EmailService;
import com.example.nexosapp.repositorios.CivilRepositorio;
import com.example.nexosapp.seguridad.JWTservice;
import com.example.nexosapp.seguridad.filter.JWTFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
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
    private JWTservice jwtService;
    private EmailService emailService;

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
     * Funcion que edita los datos de un usuario tipo civil menos dni.
     * @param id
     * @param dto
     * @return
     */

    public Civil actualizarCivil(HttpServletRequest request, CivilDTO dto) {
        Civil civil = civilRepositorio.findTopByUsuarioId(jwtService.extraerDatosHeader(request).getIdUsuario());
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

    public CivilDTO getCivilEditar(HttpServletRequest request){
        Integer idUsuario = jwtService.extraerDatosHeader(request).getIdUsuario();
        Civil civil = civilRepositorio.findTopByUsuarioId(idUsuario);
        CivilDTO dto = new CivilDTO(civil.getDni(), civil.getNombre(), civil.getApellido(), civil.getTelefono());
        return dto;
    }
    /**
     * Crear civil y a su vez el usuario asociado a este, valida si la contraseña coincide y si el nombre usuario ya existe
     * @param dto
     * @return
     */
    public Civil crearCivil(CivilCrearDTO dto) throws MessagingException {
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
        civilRepositorio.save(civil);
        emailService.sendWithHtml("nexoapp24@gmail.com",
                usuario.getEmail(),
                "Bienvenido a NexoApp",
                String.format("""
             <div style="font-family: Arial, sans-serif; line-height: 1.6;">
            <h1 style="color:#75c2f2;">¡Bienvenido a NexoApp!</h1>
            <p>Gracias por unirte a nuestra plataforma <b>%s</b>.</p>
            <br><p>Te enviaremos un mensaje cuando tu cuenta sea verificada y puedas iniciar sesión.</p>
            <p>Saludos,<br>El equipo de NexoApp</p>
            </div>
        """, usuario.getUsuario()));
        return civil;
    }

    /**
     * Método que devuelve una lista de desapariciones creadas por el usuario
     * @param id
     * @return
     */
    public List<DesaparicionListaDTO> misDesapariciones(HttpServletRequest request){

        Usuario usuario = usuarioService.getUsuarioId(jwtService.extraerDatosHeader(request).getIdUsuario());
        Set<Desaparicion> desapariciones = usuario.getDesaparicionCreada();
        return getDesaparicionListaDTOS(desapariciones);
    }

    /**
     * Método que devuelve una lista de desapariciones que sigue un usuario
     * @param id
     * @return
     */

    public List<DesaparicionListaDTO> seguimiento(HttpServletRequest request){
        Usuario usuario = usuarioService.getUsuarioId(jwtService.extraerDatosHeader(request).getIdUsuario());
        Set<Desaparicion> desapariciones = usuario.getDesapariciones();
        return getDesaparicionListaDTOS(desapariciones);
    }

    /**
     * Metodo que genera un dto para mostrar una desaparicion en front end a partir de un set de desapariciones
     * @return
     */

    private List<DesaparicionListaDTO> getDesaparicionListaDTOS(Set<Desaparicion> desapariciones) {
        desapariciones = desapariciones.stream().filter(Desaparicion::getAprobada).collect(Collectors.toSet());
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

    /**
     * Método que devuelve un DTO con los datos del usuario para mostrarlo en su menu de usuario
     * @param id
     * @return
     */

    public UsuarioMenuDTO menUsuario(HttpServletRequest request){
        Civil civil = civilRepositorio.findTopByUsuarioId(jwtService.extraerDatosHeader(request).getIdUsuario());
        Usuario usuario = civil.getUsuario();
        UsuarioMenuDTO dto = new UsuarioMenuDTO(usuario.getUsuario(), usuario.getEmail());
        return dto;
    }

    /**
     * Método que verifica un usuario
     * @param id
     * @throws MessagingException
     */
    public ResponseEntity<String> verificarUsuario(Integer id) throws MessagingException {
        Civil civil = civilRepositorio.findTopByUsuarioId(id);
        civil.getUsuario().setVerificado(true);
        usuarioService.guardar(civil.getUsuario());
        emailService.sendWithHtml(
                "nexoapp24@gmail.com",
                civil.getUsuario().getEmail(),
                "Activación de cuenta",
                String.format("""
             <div style="font-family: Arial, sans-serif; line-height: 1.6;">
            <h2 style="color:#75c2f2;">¡Tu cuenta ha sido activada!</h2>
            <p>Tu cuenta ha sido verificada y activada <b>%s</b>.</p>
            <br><p>Ya puedes iniciar sesion y comenzar a usar los servicios de nuestra web</p>
            <p>Saludos,<br>El equipo de NexoApp</p>
            </div>
        """, civil.getUsuario().getUsuario())
        );
        return ResponseEntity.ok("Usuario verificado");
    }

    public List<CivilConfirmarDTO> getCivilesSinConfirmar(){
        return civilRepositorio.findUsuariosNoVerificados().stream().map(c -> new CivilConfirmarDTO(c.getNombre(),c.getApellido(), c.getDni(), c.getUsuario().getUsuario(), c.getUsuario().getId())).toList();
    }
}
