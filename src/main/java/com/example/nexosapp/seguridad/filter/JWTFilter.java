package com.example.nexosapp.seguridad.filter;

import com.example.nexosapp.modelos.Usuario;
import com.example.nexosapp.seguridad.JWTservice;
import com.example.nexosapp.seguridad.auth.TokenDataDTO;
import com.example.nexosapp.servicios.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTservice jwtService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Método que se ejecuta en cada petición, comprueba si el token es válido y si no ha expirado. Si se accede medienta una url "/auth" se deja pasar.
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        final String authHeader = request.getHeader("Authorization");


        //Si viene por una url "/auth" lo dejamos pasar
        if (request.getServletPath().contains("/auth")){
            filterChain.doFilter(request, response);
            return;
        }

        if(authHeader== null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authHeader.substring(7);
        TokenDataDTO tokenDataDTO = jwtService.extractTokenData(token);

        if(tokenDataDTO!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            Usuario usuario = usuarioService.buscarPorUsername(tokenDataDTO.getUsername());

            if(usuario!= null && !jwtService.isExpired(token)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        usuario.getUsername(),
                        usuario.getPassword(),
                        usuario.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }

        filterChain.doFilter(request, response);
    }
}
