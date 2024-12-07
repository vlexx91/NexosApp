package com.example.nexosapp.modelos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Entity
@Table(name = "token_acceso", schema ="nexo_app", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"usuario"})
@Schema(description = "Modelo de Token")
public class Token {
    @Schema(description = "Identificador del token", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Schema(description = "Token de acceso", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c", required = true)
    @Column(name = "token")
    private String token;

    @Schema(description = "Fecha de expiración del token", example = "2021-09-01", required = true)
    @Column(name = "fecha_expiracion")
    @DateTimeFormat()
    private LocalDateTime fechaExpiracion;

    @Schema(description = "Usuario al que esta unido el token")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
