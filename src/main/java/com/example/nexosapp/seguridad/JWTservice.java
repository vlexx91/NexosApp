package com.example.nexosapp.seguridad;

import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.seguridad.auth.TokenDataDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.LinkedHashMap;

@Service
public class JWTservice {
    //Clave para codificar el token en app.prop
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    /**
     * Método que genera el token, en tokenDataDTO se guarda la información que se quiere guardar en el token
     * @param usuario
     * @return JsonWebToken
     */
    public String generateToken(Usuario usuario){
        TokenDataDTO tokenDataDTO = TokenDataDTO
                .builder()
                .idUsuario(usuario.getId())
                .username(usuario.getUsername())
                .rol(usuario.getRol().name())
                .fecha_creacion(System.currentTimeMillis())
                .fecha_expiracion(System.currentTimeMillis() + 1000 * 60 * 60 * 3)
                .build();

        return Jwts
                .builder()
                .claim("tokenDataDTO", tokenDataDTO)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    /**
     * Método que extrae los datos del token
     * @param token
     * @return Claims
     */
    private Claims extractDatosToken(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    /**
     * Método que extrae los datos del token y los guarda en un objeto TokenDataDTO
     * @param token
     * @return TokenDataDTO
     */
    public TokenDataDTO extractTokenData(String token){
        Claims claims = extractDatosToken(token);
        Map<String, Object> mapa =  (LinkedHashMap<String,Object>) claims.get("tokenDataDTO");
        return TokenDataDTO.builder()
                .idUsuario((Integer) mapa.get("idUsuario"))
                .username((String) mapa.get("username"))
                .fecha_creacion((Long) mapa.get("fecha_creacion"))
                .fecha_expiracion((Long) mapa.get("fecha_expiracion"))
                .rol((String) mapa.get("rol"))
                .build();
    }

    /**
     * Método que me dice si el token a expirado
     * @param token
     * @return
     */
    public boolean isExpired(String token){
        return new Date(extractTokenData(token).getFecha_expiracion()).before(new Date()) ;
    }
    /**
     * Método que me dice si el token es valido
     * @param
     * @return
     */
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDataDTO extraerDatosHeader(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        if (token == null) {
            throw new IllegalStateException("Usuario no autenticado");
        }
        return extractTokenData(token);
    }
}
