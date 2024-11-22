package com.example.nexosapp.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AvisoDTOAutoridadAdmin {

    private Integer id;
    private String texto;
    private LocalDate fecha;
    private String username;
}
