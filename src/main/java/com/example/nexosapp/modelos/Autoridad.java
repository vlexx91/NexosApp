package com.example.nexosapp.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="autoridad", schema="nexo_app",catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Autoridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "identificador", nullable = false)
    private String identificador;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;


}
