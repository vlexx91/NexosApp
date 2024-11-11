package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.repositorios.CivilRepositorio;
import com.example.nexosapp.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

   @Autowired
    private UsuarioRepositorio usuarioRepositorio;

   @Autowired
    private CivilRepositorio civilRepositorio;

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


}

