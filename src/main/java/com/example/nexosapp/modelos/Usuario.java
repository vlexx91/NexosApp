package com.example.nexosapp.modelos;

import com.example.nexosapp.enumerados.ROL;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario", schema = "nexo_app", catalog = "postgres")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "email")
    private String email;

    @Column(name = "contrasenya")
    private String contrasenya;

    @Column(name = "rol")
    @Enumerated(EnumType.ORDINAL)
    private ROL rol;

    @Column(name = "verificado")
    private Boolean verificado;

}
