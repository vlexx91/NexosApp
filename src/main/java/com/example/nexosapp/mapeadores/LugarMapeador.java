package com.example.nexosapp.mapeadores;

import com.example.nexosapp.DTO.LugarDTO;
import com.example.nexosapp.modelos.Lugar;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LugarMapeador {
    LugarDTO toDTO(Lugar entity);

    Lugar toEntity(LugarDTO dto);

    List<LugarDTO> toDTO(List<Lugar> entities);
    List<Lugar> toEntity(List<LugarDTO> dtos);
}
