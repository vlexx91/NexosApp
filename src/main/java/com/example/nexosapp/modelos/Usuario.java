package com.example.nexosapp.modelos;

import com.example.nexosapp.enumerados.ROL;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario", schema = "nexo_app", catalog = "postgres")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"desaparicionCreada", "desapariciones"})
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"desaparicionCreada", "desapariciones"})
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

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private Set<Desaparicion> desaparicionCreada;


@ManyToMany(fetch = FetchType.LAZY, targetEntity = Desaparicion.class)    @JoinTable(name = "usuario_desaparicion", schema = "nexo_app", catalog = "postgres",
            joinColumns = {@JoinColumn(name = "id_usuario", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_desaparicion", nullable = false)})
    private Set<Desaparicion> desapariciones;

}
