package com.example.nexosapp.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="civil", schema="nexo_app",catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Civil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dni",nullable = false)
    private String dni;
    @Column(name = "telefono",nullable = false)
    private Integer telefono;
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Column(name = "apellido",nullable = false)
    private String apellido;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
}
