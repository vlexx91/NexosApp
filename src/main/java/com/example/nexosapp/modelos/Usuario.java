package com.example.nexosapp.modelos;

import com.example.nexosapp.enumerados.ROL;
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
@EqualsAndHashCode(exclude = {"token"})
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"token"})
public class Usuario implements UserDetails {


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

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Token token;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Desaparicion.class)    @JoinTable(name = "usuario_desaparicion", schema = "nexo_app", catalog = "postgres",
            joinColumns = {@JoinColumn(name = "id_usuario", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_desaparicion", nullable = false)})
    private Set<Desaparicion> desapariciones;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.rol.name()));
    }

    @Override
    public String getPassword() {
        return this.contrasenya;
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
