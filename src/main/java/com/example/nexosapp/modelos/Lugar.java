package com.example.nexosapp.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lugar", schema = "nexo_app", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "calle")
    private String calle;

    @Column(name = "lat")
    private Double latitud;

    @Column(name = "lon")
    private Double longitud;

}
