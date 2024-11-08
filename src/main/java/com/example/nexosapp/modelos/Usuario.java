package com.example.nexosapp.modelos;

import com.example.nexosapp.enumerados.ROL;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

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


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Desaparicion.class)
    @JoinTable(name = "usuario_desaparicion", schema = "nexo_app", catalog = "postgres",
            joinColumns = {@JoinColumn(name = "id_desaparicion", nullable = false)} ,
            inverseJoinColumns ={@JoinColumn(name = "id_usuario", nullable = false)})
    private Set<Usuario> usuarios;

}
