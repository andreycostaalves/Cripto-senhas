package io.github.andreycostaalves.Criptosenhas.data;

import io.github.andreycostaalves.Criptosenhas.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheUsuarioData implements UserDetails {


    private final Optional<UsuarioModel> usuario;

    public DetalheUsuarioData(Optional<UsuarioModel> usuario) {
        this.usuario = usuario;
    }

    //permissoes de usuario.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }


    @Override
    public String getPassword() {
        return usuario.orElse(new UsuarioModel()).getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.orElse(new UsuarioModel()).getLogin();
    }

    //Se a conta está expirada
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Se a conta está bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Se a credencial não está expirada.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Se o usuario está ativo.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
