package com.example.nexosapp.modelos;

import com.example.nexosapp.enumerados.ROL;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario", schema = "nexo_app", catalog = "postgres")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"token","desaparicionCreada", "desapariciones"})
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"token","desaparicionCreada", "desapariciones"})
@Schema(description = "Modelo de Usuario")
public class Usuario implements UserDetails {

    @Schema(description = "Identificador del usuario", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Schema(description = "Nombre de usuario", example = "usuario", required = true)
    @Column(name = "usuario")
    private String usuario;

    @Schema(description = "Nombre del usuario", example = "nombre", required = true)
    @Column(name = "email")
    private String email;

    @Schema(description = "Contraseña del usuario", example = "contrasenya", required = true)
    @Column(name = "contrasenya")
    private String contrasenya;

    @Schema(description = "Rol del usuario", example = "ADMIN", required = true)
    @Column(name = "rol")
    @Enumerated(EnumType.ORDINAL)
    private ROL rol;

    @Schema(description = "Bool para comprobar si el usuario esta verificado", example = "true", required = true)
    @Column(name = "verificado")
    private Boolean verificado;

    @Schema(description = "Set de desapariciones creadas por el usuario")
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    private Set<Desaparicion> desaparicionCreada;
    @Schema(description = "Token del usuario")
    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Token token;

    @Schema(description = "Set de desapariciones que sigue el usuario")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Desaparicion.class)    @JoinTable(name = "usuario_desaparicion", schema = "nexo_app", catalog = "postgres",
            joinColumns = {@JoinColumn(name = "id_usuario", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_desaparicion", nullable = false)})
    private Set<Desaparicion> desapariciones;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.rol.name()));
    }

    /**
     * Metodo que devuelve la contraseña del usuario
     * @return
     */
    @Override
    public String getPassword() {
        return this.contrasenya;
    }

    /**
     * Metodo que devuelve el nombre de usuario
     * @return
     */
    @Override
    public String getUsername() {
        return this.usuario;
    }

    /**
     * Metodo que devuelve si la cuenta del usuario ha expirado
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    /**
     * Metodo que devuelve si la cuenta del usuario esta bloqueada
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    /**
     * Metodo que devuelve si las credenciales del usuario han expirado
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * Metodo que devuelve si el usuario esta habilitado
     * @return
     */
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
