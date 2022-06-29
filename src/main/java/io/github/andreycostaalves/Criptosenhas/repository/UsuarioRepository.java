package io.github.andreycostaalves.Criptosenhas.repository;

import io.github.andreycostaalves.Criptosenhas.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<UsuarioModel, Integer> {

    public Optional<UsuarioModel> findByLogin(String login);
}
