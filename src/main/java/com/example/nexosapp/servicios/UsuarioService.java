package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.DesaparicionPrincipalDTO;
import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.repositorios.CivilRepositorio;
import com.example.nexosapp.repositorios.DesaparicionRepositorio;
import com.example.nexosapp.repositorios.UsuarioRepositorio;
import com.example.nexosapp.seguridad.JWTservice;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.nexosapp.servicios.DesaparicionServicio.extraerPrincipalDTO;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    private UsuarioRepositorio usuarioRepositorio;

    private CivilRepositorio civilRepositorio;

   private DesaparicionRepositorio desaparicionRepositorio;

   private JWTservice jwtService;

    /**
     * Obtener todos los Usuarios
     * @return
     */

    public List<Usuario>getUsuarios(){
        return usuarioRepositorio.findAll();
    }

    /**
     * Obtener usuario por id
     * @return
     */

    public Usuario getUsuarioId(Integer id){
        return usuarioRepositorio.findById(id).orElse(null);
    }


    /**
     * Crea o edita un  usuario
     * @return
     */

    public Usuario guardar(Usuario usuario){
        return usuarioRepositorio.save(usuario);
    }

    /**
     *Elimina un usuario por id
     * @return
     */

    public String eliminaUsuarioIdCivil(Integer id) {
        String mensaje;
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        Civil civil = civilRepositorio.findTopByUsuarioId(id);

        if (civil != null) {
            civilRepositorio.delete(civil);
        }

        if (usuario == null) {
            mensaje = "Ese usuario no existe";
            return mensaje;
        }

        for (Desaparicion d : usuario.getDesaparicionCreada()){
            List<Usuario> usuarios = usuarioRepositorio.findByDesapariciones_Id(d.getId());
            for (Usuario u : usuarios){
                u.getDesapariciones().remove(d);
                usuarioRepositorio.save(u);
            }

        }

        try {
            usuarioRepositorio.deleteById(id);
            Usuario usuariotry = usuarioRepositorio.findById(id).orElse(null);

            if (usuariotry != null) {
                mensaje = "No se ha podido eliminar el usuario";
            } else {
                mensaje = "Usuario eliminado";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar el usuario";
        }

        return mensaje;
    }

    /**
     * Añadir una desaparicion a un usuario, osea a su lista de seguimiento
     *
     * @param idDesaparicion
     * @return
     */

    public String anyadirSeguimiento(HttpServletRequest request, Integer idDesaparicion){
        String mensaje;
        Usuario usuario = usuarioRepositorio.findById(jwtService.extraerDatosHeader(request).getIdUsuario()).orElse(null);
        Desaparicion desaparicion = desaparicionRepositorio.getReferenceById(idDesaparicion);
        if (usuario == null) {
            mensaje = "Ese usuario no existe";
            return mensaje;
        }
        if (desaparicion == null) {
            mensaje = "Esa desaparicion no existe";
            return mensaje;
        }else {
            usuario.getDesapariciones().add(desaparicion);
            usuarioRepositorio.save(usuario);
            mensaje = "Desaparicion añadida";
        }
        return mensaje;
    }

    /**
     * Eliminar una desaparicion de un usuario, osea de su lista de seguimiento
     *
     * @param idDesaparicion
     * @return
     */

    public String eliminarSeguimiento(HttpServletRequest request, Integer idDesaparicion) {
        String mensaje;
        Usuario usuario = usuarioRepositorio.findById(jwtService.extraerDatosHeader(request).getIdUsuario()).orElse(null);
        Desaparicion desaparicion = desaparicionRepositorio.getReferenceById(idDesaparicion);
        if (usuario == null) {
            mensaje = "Ese usuario no existe";
            return mensaje;
        }
        if (desaparicion == null) {
            mensaje = "Esa desaparicion no existe";
            return mensaje;
        } else {
            usuario.getDesapariciones().remove(desaparicion);
            usuarioRepositorio.save(usuario);
            mensaje = "Desaparicion eliminada";
        }
        return mensaje;
    }

    /**
     * Obtiene el usurio por su username
     * @param username
     * @return Usuario
     */
    public Usuario buscarPorUsername(String username) {
        return usuarioRepositorio.findTopByUsuario(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public boolean validarContrasenya(Usuario usuario, String passwordSinEncriptar){
        return passwordEncoder.matches(passwordSinEncriptar, usuario.getPassword());
    }

    /**
     * Obtiene el rol de un usuario
     * @param usuario
     * @return
     */

    public String getRol(String usuario){
        Usuario usuario1 = usuarioRepositorio.findTopByUsuario(usuario).orElse(null);
        if (usuario1 == null){
            return "Usuario no encontrado";
        }
        return usuario1.getRol().toString();
    }

//    public String guardarUsario(UsuarioDTO usuarioDTO){
//        if (usuarioDTO.getContrasenya() !=usuarioDTO.getRepContrasenya()){
//            return "Las contraseñas no coinciden";
//        }
//        if (usuarioRepositorio.findTopByUsuario(usuarioDTO.getUsuario())!=null){
//            return "Nombre de usuario ya existe";
//        }
//        Usuario usuario = new Usuario();
//        usuario.setUsuario(usuarioDTO.getUsuario());
//        usuario.setContrasenya(passwordEncoder.encode(usuarioDTO.getContrasenya()));
//        usuario.setEmail(usuarioDTO.getEmail());
//        usuario.setRol(ROL.CIVIL);
//        usuario.setVerificado(false);
//        usuarioRepositorio.save(usuario);
//        return "Usuario creado correctamente";
//    }
    public String eliminaUsuarioId(Integer id){
        try {
            usuarioRepositorio.deleteById(id);
            return "Usuario eliminado.";

        } catch (Exception e){
            return "No se ha podido elimar.";
        }
    }

    public String verificaUsuarioId(Integer id) {
        try {
            Usuario u = usuarioRepositorio.findById(id).orElse(null);
            u.setVerificado(true);
            usuarioRepositorio.save(u);
            return "Usuario verificado";
        } catch (Exception e){
            return "No se ha podido verificar el usuario";
        }
    }
}

