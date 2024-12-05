package com.example.nexosapp.servicios;

import com.example.nexosapp.DTO.*;
import com.example.nexosapp.enumerados.ESTADO;
import com.example.nexosapp.enumerados.ROL;
import com.example.nexosapp.enumerados.Sexo;
import com.example.nexosapp.mapeadores.DesaparicionMapeador;
import com.example.nexosapp.mapeadores.LugarMapeador;
import com.example.nexosapp.modelos.*;
import com.example.nexosapp.recursos.CloudinaryService;
import com.example.nexosapp.recursos.OpenCageService;
import com.example.nexosapp.repositorios.ComentarioRepositorio;
import com.example.nexosapp.repositorios.DesaparicionRepositorio;
import com.example.nexosapp.repositorios.UsuarioRepositorio;
import com.example.nexosapp.seguridad.JWTservice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DesaparicionServicio {
    private DesaparicionRepositorio desaparicionRepositorio;
    private DesaparicionMapeador desaparicionMapeador;
    private LugarServicio lugarServicio;
    private OpenCageService openCageService;
    private CloudinaryService cloudinaryService;
    private UsuarioRepositorio usuarioRepositorio;
    private JWTservice jwtService;
    @PersistenceContext
    private EntityManager entityManager;
    private ComentarioRepositorio comentarioRepositorio;

    /**
     * Método que devuelve una lista de desapariciones
     *
     * @return
     */

    public List<Desaparicion> getDesapariciones() {
        return desaparicionRepositorio.findAll();
    }

    /**
     * Método que devuelve una desaparición por su id
     *
     * @param id
     * @return
     */
    public Desaparicion getDesaparicionId(Integer id) {
        return desaparicionRepositorio.findById(id).orElse(null);
    }

    /**
     * Método que guarda una desaparición
     *
     * @param desaparicion
     * @return
     */
    public Desaparicion guardar(Desaparicion desaparicion) {
        return desaparicionRepositorio.save(desaparicion);
    }

    /**
     * Método que elimina una desaparición por su id
     *
     * @param id
     * @return
     */
    public ResponseEntity<String> eliminar(Integer id) {
        String mensaje;
        Desaparicion desaparicion = getDesaparicionId(id);
        if (desaparicion == null) {
            return ResponseEntity.badRequest().body("No existe ese usuario");
        }
        try {
            //aqui primero limpio las desapariciones que sigue el usuario y luego elimino de las relaciones de segimiento de otros  usuarios con esa desaparicion
            List<Usuario> usuarios = usuarioRepositorio.findByDesapariciones_Id(id);
            for (Usuario u : usuarios) {
                u.getDesapariciones().remove(desaparicion);
                usuarioRepositorio.save(u);
            }

            List<Comentario> comentarios = comentarioRepositorio.findByDesaparicionId(id);
            comentarioRepositorio.deleteAll(comentarios);

            desaparicionRepositorio.deleteById(id);
            desaparicion = getDesaparicionId(id);
            if (desaparicion != null) {
                mensaje = "No se ha podido eliminar la desaparición correctamente.";
            } else {
                mensaje = "Eliminado correctamente.";
            }
        } catch (Exception e) {
            mensaje = "No se ha podido eliminar la desaparición.";
        }
        return ResponseEntity.ok(mensaje);
    }

    /**
     * Método que marca como eliminada una desaparición por su id
     *
     * @param id
     * @return
     */

    public ResponseEntity<String> eliminarDesaparicion(Integer id) {
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElse(null);
        if (desaparicion == null) {
            return ResponseEntity.badRequest().body("No existe esa desaparición");
        }
        desaparicion.setEliminada(true);
        guardar(desaparicion);
        return ResponseEntity.ok("Desaparición eliminada");
    }

    /**
     * Método que guarda una desaparición a partir de un dto, guarda en cascada personal lugar y fotos y conecta con las apis para obtener las coordenadas y subir la foto
     *
     * @param dto
     * @return
     */
    public ResponseEntity<String> guardarDesaparicion(HttpServletRequest request, DesaparicionDTO dto, List<MultipartFile> files) throws IOException {
        Desaparicion desaparicion = desaparicionMapeador.toEntity(dto);
        desaparicion.setUsuario(usuarioRepositorio.findById(jwtService.extraerDatosHeader(request).getIdUsuario()).orElse(null));
        desaparicion.setAprobada(desaparicion.getUsuario().getRol() == ROL.AUTORIDAD);
        desaparicion.getPersona().setDescripcion(dto.getPersonaDTO().getDescripcionFisica());
        LocalDate fechaHoy = LocalDate.now();
        if (desaparicion.getFecha().isAfter(fechaHoy)) {
            throw new IllegalArgumentException("La fecha de desaparición no puede ser posterior a la fecha actual.");
        }

        if (desaparicion.getFecha().isBefore(desaparicion.getPersona().getFechaNacimiento())) {
            throw new IllegalArgumentException("La fecha de desaparición no puede ser anterior a la fecha de nacimiento.");
        }
        Map<String, Double> coordenadas = openCageService.getLatLon(desaparicion.getLugar().getCalle() + ", " + desaparicion.getLugar().getLocalidad() + ", " + desaparicion.getLugar().getProvincia() + ", España");
        desaparicion.getLugar().setLatitud(coordenadas.get("lat"));
        desaparicion.getLugar().setLongitud(coordenadas.get("lon"));
        desaparicion.getUsuario().getDesaparicionCreada().add(desaparicion);

        desaparicion.setEstado(ESTADO.DESAPARECIDO);
        Set<Foto> listaFotos = new HashSet<>();
        for (MultipartFile f : files) {
            Foto foto = new Foto();
            foto.setUrl(cloudinaryService.uploadImage(f));
            if (files.indexOf(f) == 0) {
                foto.setEsCara(true);
            } else {
                foto.setEsCara(false);
            }
            listaFotos.add(foto);

        }
        desaparicion.getPersona().setFotos(listaFotos);
        desaparicion.setEliminada(false);
        desaparicionRepositorio.save(desaparicion);
        return ResponseEntity.ok("Desaparicion creada con exito");
    }

    /**
     * Metodo que a partir de todas las desapariciones, devuelve una lista con una
     * dto preparada paara mostrarla en la página mostrar mas
     *
     * @return List<DesaparionMostrarMasDTO>
     */

    public List<DesaparionMostrarMasDTO> mostrarMas() {
        List<Desaparicion> desapariciones = desaparicionRepositorio.findAllByEliminadaIsFalse();
        List<DesaparionMostrarMasDTO> devolucion = new ArrayList<>();
        for (Desaparicion desaparicion : desapariciones) {
            List<String> fotos = new ArrayList<>();
            DesaparionMostrarMasDTO d = new DesaparionMostrarMasDTO();
            d.setId(desaparicion.getId());
            d.setNombre(desaparicion.getPersona().getNombre());
            d.setApellido(desaparicion.getPersona().getApellido());
            d.setDescripcion(desaparicion.getDescripcion());
            d.setFecha(desaparicion.getFecha().toString());
            LugarDTO l = new LugarDTO(desaparicion.getLugar().getProvincia()
                    , desaparicion.getLugar().getLocalidad()
                    , desaparicion.getLugar().getCalle());
            d.setLugar(l);
            desaparicion.getPersona().getFotos().forEach(f -> fotos.add(f.getUrl()));
            d.setFotos(fotos);
            devolucion.add(d);
        }
        return devolucion;
    }

    /**
     * Metodo que a partir de todas las desapariciones, devuelve una lista con una
     * dto preparada paara mostrarla en la página principal
     *
     * @return List<DesaparicionPrincipalDTO>
     */
    public List<DesaparicionPrincipalDTO> paginaPrincipal() {
        List<Desaparicion> desapariciones = desaparicionRepositorio.findTop10ByEliminadaIsFalseAndAprobadaIsTrueAndEstadoOrderByFechaDesc(ESTADO.DESAPARECIDO);
        return extraerPrincipalDTO(desapariciones);
    }

    /**
     * Metodo que a partir de todas las desapariciones, devuelve una lista con una
     * dto preparada paara mostrarla en la página principal o en seguimiento
     *
     * @return List<DesaparicionPrincipalDTO>
     */
    static List<DesaparicionPrincipalDTO> extraerPrincipalDTO(List<Desaparicion> desapariciones) {
        List<DesaparicionPrincipalDTO> devolucion = new ArrayList<>();

        desapariciones.forEach(d -> {
            DesaparicionPrincipalDTO des = new DesaparicionPrincipalDTO();
            des.setId(d.getId());
            des.setNombre(d.getPersona().getNombre());
            des.setApellidos(d.getPersona().getApellido());
            des.setFecha(d.getFecha().toString());
            des.setUrlFotoCara(d.getPersona().getFotos()
                    .stream()
                    .filter(Foto::getEsCara)
                    .findFirst()
                    .get().getUrl());
            devolucion.add(des);
        });
        return devolucion;
    }

    /**
     * Metodo para editar una desaparición
     *
     * @return Desaparicion
     */

    public ResponseEntity<String> editarDesaparicion(Integer id, EditarDesaparicionDTO editarDesaparicionDTO) {
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElse(null);

        if (desaparicion == null) {
            return null;
        }

        if (!Objects.equals(desaparicion.getLugar().getProvincia(), editarDesaparicionDTO.getLugarLatLongDTO().getProvincia()) || !Objects.equals(desaparicion.getLugar().getLocalidad(), editarDesaparicionDTO.getLugarLatLongDTO().getLocalidad()) || !Objects.equals(desaparicion.getLugar().getCalle(), editarDesaparicionDTO.getLugarLatLongDTO().getCalle())) {
            Map<String, Double> coordenadas = openCageService.getLatLon(editarDesaparicionDTO.getLugarLatLongDTO().getCalle() + ", " + editarDesaparicionDTO.getLugarLatLongDTO().getLocalidad() + ", " + editarDesaparicionDTO.getLugarLatLongDTO().getProvincia() + ", España");
            desaparicion.getLugar().setLatitud(coordenadas.get("lat"));
            desaparicion.getLugar().setLongitud(coordenadas.get("lon"));
            desaparicion.getLugar().setProvincia(editarDesaparicionDTO.getLugarLatLongDTO().getProvincia());
            desaparicion.getLugar().setLocalidad(editarDesaparicionDTO.getLugarLatLongDTO().getLocalidad());
            desaparicion.getLugar().setCalle(editarDesaparicionDTO.getLugarLatLongDTO().getCalle());
            lugarServicio.guardar(desaparicion.getLugar());
        }


        desaparicion.setEstado(editarDesaparicionDTO.getEstado());

        desaparicion.setDescripcion(editarDesaparicionDTO.getDescripcion());


        desaparicionRepositorio.save(desaparicion);

        return ResponseEntity.ok("Desaparición editada con éxito");
    }

    /**
     * Método para obtener los datos para editar una desaparición
     *
     * @param id
     * @return EditarDesaparicionDTO
     */

    public EditarDesaparicionDTO getEditarDesaparicionDTO(Integer id) {
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElse(null);
        if (desaparicion == null) {
            return null;
        }
        EditarDesaparicionDTO dto = new EditarDesaparicionDTO();
        LugarLatLongDTO lugar = new LugarLatLongDTO();
        lugar.setProvincia(desaparicion.getLugar().getProvincia());
        lugar.setLocalidad(desaparicion.getLugar().getLocalidad());
        lugar.setCalle(desaparicion.getLugar().getCalle());
        lugar.setLatitud(desaparicion.getLugar().getLatitud());
        lugar.setLongitud(desaparicion.getLugar().getLongitud());
        dto.setLugarLatLongDTO(lugar);
        dto.setDescripcion(desaparicion.getDescripcion());
        dto.setEstado(desaparicion.getEstado());
        return dto;
    }

    public DesaparicionEditarAutoridadDTO getDesaparicionEditarAutoridadDTO(Integer id) {
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElse(null);
        if (desaparicion == null) {
            return null;
        }
        DesaparicionEditarAutoridadDTO dto = new DesaparicionEditarAutoridadDTO();
        LugarLatLongDTO lugar = new LugarLatLongDTO();
        lugar.setProvincia(desaparicion.getLugar().getProvincia());
        lugar.setLocalidad(desaparicion.getLugar().getLocalidad());
        lugar.setCalle(desaparicion.getLugar().getCalle());
        lugar.setLatitud(desaparicion.getLugar().getLatitud());
        lugar.setLongitud(desaparicion.getLugar().getLongitud());
        dto.setLugarLatLongDTO(lugar);
        dto.setDescripcion(desaparicion.getDescripcion());
        dto.setEstado(desaparicion.getEstado());
        dto.setFotos(desaparicion.getPersona().getFotos().stream().toList());
        return dto;
    }


    /**
     * Método para verificar una desaparición
     *
     * @return String mensaje
     */

    public ResponseEntity<String> verificarDesaparicion(Integer id) {
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElse(null);

        if (desaparicion == null) {
            return ResponseEntity.badRequest().body("No existe esa desaparición");
        }

        desaparicion.setAprobada(true);
        desaparicionRepositorio.save(desaparicion);

        return ResponseEntity.ok("Desaparición aprobada con éxito");
    }

    /**
     * Método que devuelve una lista de desapariciones pendientes (no verificadas)
     */

    public List<Desaparicion> getDesaparicionesPendientes() {
        return desaparicionRepositorio.desaparicionesPendientes();
    }

    /**
     * Método que devuelve una desaparicion en funcion de su id
     *
     * @param id
     * @return
     */

    public DesaparicionIndividualDTO getDesaparicion(Integer id) {
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElse(null);
        if (desaparicion == null) {
            return null;
        }
        DesaparicionIndividualDTO dto = new DesaparicionIndividualDTO();
        List<String> fotos = new ArrayList<>();
        PersonaDTO persona = new PersonaDTO();
        persona.setNombre(desaparicion.getPersona().getNombre());
        persona.setDni(desaparicion.getPersona().getDni());
        persona.setApellido(desaparicion.getPersona().getApellido());
        persona.setDescripcionFisica(desaparicion.getPersona().getDescripcion());
        persona.setFechaNacimiento(desaparicion.getPersona().getFechaNacimiento().toString());
        persona.setAltura(desaparicion.getPersona().getAltura());
        persona.setComplexion(desaparicion.getPersona().getComplexion());
        persona.setSexo(desaparicion.getPersona().getSexo());
        dto.setPersona(persona);
        desaparicion.getPersona().getFotos().forEach(f -> fotos.add(f.getUrl()));
        dto.setFotos(fotos);
        dto.setFecha(desaparicion.getFecha().toString());
        dto.setDescripcion(desaparicion.getDescripcion());
        dto.setEstado(desaparicion.getEstado());
        dto.setAprobada(desaparicion.getAprobada());
        return dto;
    }

    /**
     * Método que devuelve una lista de desapariciones no aprobadas
     *
     * @return
     */

    public List<DesaparicionSinVerificarDTO> getSinAprobar() {
        List<Desaparicion> desapariciones = desaparicionRepositorio.findAllByAprobadaIsFalseAndEliminadaIsFalse();
        return getDesaparicionSinVerificarDTOS(desapariciones);
    }

    /**
     * Método que devuelve una lista de desapariciones eliminadas
     *
     * @return
     */

    public List<DesaparicionSinVerificarDTO> listaEliminadas() {
        List<Desaparicion> desapariciones = desaparicionRepositorio.findAllByEliminadaIsTrue();
        return getDesaparicionSinVerificarDTOS(desapariciones);
    }

    /**
     * metodo qye genera dtos para enviar al front
     *
     * @param desapariciones
     * @return
     */
    private List<DesaparicionSinVerificarDTO> getDesaparicionSinVerificarDTOS(List<Desaparicion> desapariciones) {
        List<DesaparicionSinVerificarDTO> devolucion = new ArrayList<>();
        desapariciones.forEach(d -> {
            DesaparicionSinVerificarDTO dto = new DesaparicionSinVerificarDTO();
            dto.setId(d.getId());
            dto.setDni(d.getPersona().getDni());
            dto.setNombre(d.getPersona().getNombre());
            dto.setApellido(d.getPersona().getApellido());
            devolucion.add(dto);
        });
        return devolucion;
    }

    public ResponseEntity<String> recuperarEliminacion(Integer id) {
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElse(null);
        if (desaparicion == null) {
            return ResponseEntity.badRequest().body("No existe esa desaparición");
        }
        desaparicion.setEliminada(false);
        desaparicionRepositorio.save(desaparicion);
        return ResponseEntity.ok("Desaparición recuperada");
    }
//    public List<Desaparicion> filtrarDesapariciones(LocalDate fecha, ESTADO estado, String provincia,
//                                                    String dni, String nombrePersona, String apellidoPersona, Sexo sexo) {
//        if (estado == null) {
//            estado = ESTADO.DESAPARECIDO;
//        }
//
//        if (sexo == null) {
//            sexo = Sexo.HOMBRE;
//        }
//        return desaparicionRepositorio.buscarDesapariciones(fecha, estado.ordinal(), provincia, dni, nombrePersona, apellidoPersona,sexo.ordinal());
//    }
//public List<Desaparicion> buscarDesapariciones(LocalDate fecha, Integer estadoOrdinal, String provincia,
//                                               String dni, String nombrePersona, String apellidoPersona, Integer sexo) {
//    ESTADO estado = estadoOrdinal != null ? ESTADO.values()[estadoOrdinal] : null;
//    return desaparicionRepositorio.buscarDesapariciones(fecha, estadoOrdinal, provincia, dni, nombrePersona, apellidoPersona, sexo);
//}

    //public List<Desaparicion> buscarConFiltros(FiltroDTO filtro) {
//    return desaparicionRepositorio.buscarConFiltros(
//            filtro.getDni(),
//            filtro.getNombre(),
//            filtro.getApellidos(),
//            filtro.getSexo(),
//            filtro.getComplexion(),
//            filtro.getEstado(),
//            filtro.getProvincia(),
//            filtro.getLocalidad(),
//            filtro.getFecha()
//    );
//}
    public List<Desaparicion> buscarPorFechaEstadoYNombre(LocalDate fecha, String estado, String nombre) {
        ESTADO estadoEnum = null;
        if (estado != null && !estado.isEmpty()) {
            try {
                estadoEnum = ESTADO.valueOf(estado);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("El estado proporcionado no es válido: " + estado);
            }
        }

        if (fecha == null) {
            return desaparicionRepositorio.buscarPorEstadoYNombre(estadoEnum, nombre);
        } else {
            return desaparicionRepositorio.buscarPorFechaEstadoYNombre(fecha, estadoEnum, nombre);
        }
    }

    public List<Desaparicion> obtenerUltimas30() {
        Pageable pageable = PageRequest.of(0, 30); // Crear una paginación para las últimas 30 desapariciones verificadas
        return desaparicionRepositorio.findLast30Verified(pageable).getContent();
    }

    public List<DesaparicionGestionDTO> getDesaparicionesGestion() {
        List<Desaparicion> desaparicionesNoEliminadas = desaparicionRepositorio.findAllByEliminadaIsFalse();
        return desaparicionesNoEliminadas.stream().map(d -> new DesaparicionGestionDTO(d.getId(), d.getPersona().getNombre(), d.getPersona().getApellido(), d.getFecha().toString())).toList();

    }

    public ResponseEntity<String> editarDesaparicionGestion(Integer id, DesaparicionEditarAutoridadDTO dto, List<MultipartFile> files) throws IOException {
        Desaparicion desaparicion = desaparicionRepositorio.findById(id).orElse(null);

        if (desaparicion == null) {
            return ResponseEntity.ok("Fallo al encontrar la desaparición");
        }

        if (!Objects.equals(desaparicion.getLugar().getProvincia(), dto.getLugarLatLongDTO().getProvincia()) || !Objects.equals(desaparicion.getLugar().getLocalidad(), dto.getLugarLatLongDTO().getLocalidad()) || !Objects.equals(desaparicion.getLugar().getCalle(), dto.getLugarLatLongDTO().getCalle())) {
            Map<String, Double> coordenadas = openCageService.getLatLon(dto.getLugarLatLongDTO().getCalle() + ", " + dto.getLugarLatLongDTO().getLocalidad() + ", " + dto.getLugarLatLongDTO().getProvincia() + ", España");
            desaparicion.getLugar().setLatitud(coordenadas.get("lat"));
            desaparicion.getLugar().setLongitud(coordenadas.get("lon"));
            desaparicion.getLugar().setProvincia(dto.getLugarLatLongDTO().getProvincia());
            desaparicion.getLugar().setLocalidad(dto.getLugarLatLongDTO().getLocalidad());
            desaparicion.getLugar().setCalle(dto.getLugarLatLongDTO().getCalle());
            lugarServicio.guardar(desaparicion.getLugar());
        }
        Set<Foto> fotos = new HashSet<>(desaparicion.getPersona().getFotos());
        if (fotos.size() != dto.getFotos().size()) {
            fotos.retainAll(dto.getFotos());
        }
        if (!files.isEmpty()) {
            for (MultipartFile f : files) {
                Foto foto = new Foto();
                foto.setUrl(cloudinaryService.uploadImage(f));
                foto.setEsCara(false);
                fotos.add(foto);
            }

        }
        desaparicion.setEstado(dto.getEstado());
        desaparicion.setDescripcion(dto.getDescripcion());
        desaparicion.getPersona().setFotos(fotos);

        desaparicionRepositorio.save(desaparicion);

        return ResponseEntity.ok("Desaparición editada con éxito");
    }

}





