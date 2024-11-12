package com.example.nexosapp.seguridad.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDataDTO {
    private Integer idUsuario;
    private String username;
    private String rol;
    private Long fecha_creacion;
    private Long fecha_expiracion;
}
