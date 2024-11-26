package com.example.nexosapp.DTO;

import com.example.nexosapp.enumerados.Complexion;
import com.example.nexosapp.enumerados.ESTADO;
import com.example.nexosapp.enumerados.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroDTO {
        private String dni;
        private String nombre;
        private String apellidos;
        private Sexo sexo;
        private Complexion complexion;
        private ESTADO estado;
        private String provincia;
        private String localidad;
        private LocalDate fecha;



}
