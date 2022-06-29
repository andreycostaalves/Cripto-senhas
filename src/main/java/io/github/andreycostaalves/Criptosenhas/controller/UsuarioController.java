package io.github.andreycostaalves.Criptosenhas.controller;

import io.github.andreycostaalves.Criptosenhas.model.UsuarioModel;
import io.github.andreycostaalves.Criptosenhas.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/listartodos")
    public ResponseEntity<List<UsuarioModel>> listarTodos(){

        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvar (@RequestBody UsuarioModel usuario){
        return ResponseEntity.ok(repository.save(usuario));
    }
}
