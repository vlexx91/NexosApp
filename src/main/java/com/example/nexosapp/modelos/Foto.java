package com.example.nexosapp.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="foto", schema="nexo_app",catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "url",nullable = false)
    private String url;
    @Column(name = "es_cara",nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean esCara;

}
