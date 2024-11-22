package com.example.nexosapp.mapeadores;

import com.example.nexosapp.DTO.DesaparicionDTO;
import com.example.nexosapp.DTO.LugarDTO;
import com.example.nexosapp.DTO.PersonaDTO;
import com.example.nexosapp.modelos.Desaparicion;
import com.example.nexosapp.modelos.Lugar;
import com.example.nexosapp.modelos.Persona;
import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.repositorios.UsuarioRepositorio;
import com.example.nexosapp.servicios.UsuarioService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DesaparicionMapeador {

    LugarMapeador lugarMapeador = Mappers.getMapper(LugarMapeador.class);
    PersonaMapeador personaMapeador = Mappers.getMapper(PersonaMapeador.class);

    @Autowired
    UsuarioService usuarioService;


    @Mapping(source = "personaDTO", target = "persona" , qualifiedByName = "transformarPersonaDTO")
    @Mapping(source = "lugarDTO", target = "lugar" , qualifiedByName = "transformarLugarDTO")

    public abstract Desaparicion toEntity(DesaparicionDTO dto);

    @Mapping(source = "persona", target = "personaDTO" , qualifiedByName = "transformarPersona")
    @Mapping(source = "lugar", target = "lugarDTO" , qualifiedByName = "transformarLugar")
    public abstract DesaparicionDTO toDTO(Desaparicion entity);

    public abstract List<Desaparicion> toEntity(List<DesaparicionDTO> dtos);

    public abstract List<DesaparicionDTO> toDTO(List<Desaparicion> entities);

    @Named("transformarPersonaDTO")
    Persona transformarUsuario(PersonaDTO dto){
        return personaMapeador.toEntity(dto);
    }

    @Named("transformarLugarDTO")
    Lugar transformarLugar(LugarDTO dto){
        return lugarMapeador.toEntity(dto);
    }

    @Named("transformarPersona")
    PersonaDTO transformarUsuario(Persona entity) {
        return personaMapeador.toDTO(entity);
    }

    @Named("transformarLugar")
    LugarDTO transformarUsuario(Lugar entity) {
        return lugarMapeador.toDTO(entity);
    }

}
