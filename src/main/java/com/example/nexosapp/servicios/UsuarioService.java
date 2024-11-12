package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    private UsuarioRepositorio usuarioRepositorio;


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

    public String eliminaUsuarioId(Integer id) {
        String mensaje;
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        if (usuario == null) {
            mensaje = "Ese usuario no existe";
            return mensaje;
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
}

