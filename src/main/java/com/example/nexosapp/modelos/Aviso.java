package com.example.nexosapp.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "aviso", schema = "nexo_app", catalog = "postgres")
//etiquetas de lombok
@Getter
@Setter
@ToString(exclude = {"usuario"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"usuario"})
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "texto", nullable = false)
    private String texto;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Foto.class)
    @JoinTable(
            name = "foto_aviso",
            schema = "nexo_app",
            joinColumns = {@JoinColumn(name = "id_aviso", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_foto", nullable = false)}
    )
    private Set<Foto> fotos;
}

