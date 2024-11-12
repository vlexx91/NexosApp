package com.example.nexosapp.servicios;

import com.example.nexosapp.modelos.Civil;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.repositorios.CivilRepositorio;
import com.example.nexosapp.repositorios.DesaparicionRepositorio;
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

   @Autowired
   private DesaparicionRepositorio desaparicionRepositorio;

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

        for (Desaparicion d : usuario.getDesaparicionCreada()){
            List<Usuario> usuarios = usuarioRepositorio.findByDesapariciones_Id(d.getId());
            for (Usuario u : usuarios){
                u.getDesapariciones().remove(d);
                usuarioRepositorio.save(u);
            }

        }

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

    public String anyadirSeguimiento(Integer  idUsuario, Integer idDesaparicion){
        String mensaje;
        Usuario usuario = usuarioRepositorio.findById(idUsuario).orElse(null);
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


}

