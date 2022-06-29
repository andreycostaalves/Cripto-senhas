package io.github.andreycostaalves.Criptosenhas.service;

import io.github.andreycostaalves.Criptosenhas.data.DetalheUsuarioData;
import io.github.andreycostaalves.Criptosenhas.model.UsuarioModel;
import io.github.andreycostaalves.Criptosenhas.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Serviço padrão do spring security para validação de usuario e senha.

@Component
public class DetalheUsuarioServiceIMPL implements UserDetailsService {

    private final UsuarioRepository repository;

    public DetalheUsuarioServiceIMPL(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsuarioModel> usuario = repository.findByLogin(username);
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário [" + username + "] Não encontrado.");
        }

        return new DetalheUsuarioData(usuario);
    }
}
