package com.example.nexosapp.mapeadores;

import com.example.nexosapp.DTO.LugarDTO;
import com.example.nexosapp.DTO.PersonaDTO;
import com.example.nexosapp.modelos.Lugar;
import com.example.nexosapp.modelos.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapeador {

    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento" , qualifiedByName = "StringFecha")
    PersonaDTO toDTO(Persona entity);

    @Mapping(source = "fechaNacimiento", target = "fechaNacimiento" , qualifiedByName = "ParseoFecha")
    Persona toEntity(PersonaDTO dto);

    List<PersonaDTO> toDTO(List<Persona> entities);
    List<Persona> toEntity(List<PersonaDTO> dtos);

    @Named("ParseoFecha")
    default LocalDate ParseoFecha(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaNacimiento = LocalDate.parse(fecha, formatter);
        return fechaNacimiento;
    }

    @Named("StringFecha")
    default String ParseoFecha(LocalDate fecha){
        String fechaNacimiento = fecha.toString();
        return fechaNacimiento;
    }

}
