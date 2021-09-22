package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable(name = "id") Long id) {
        Cozinha cozinha = cozinhaRepository.buscar(id);
        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cozinha salvar(@RequestBody Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaEncontrada = cozinhaRepository.buscar(id);
        if (cozinhaEncontrada != null) {
            BeanUtils.copyProperties(cozinha, cozinhaEncontrada, "id");
            cozinhaRepository.salvar(cozinhaEncontrada);
            return ResponseEntity.ok(cozinhaEncontrada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id){
        try {
            Cozinha cozinha = cozinhaRepository.buscar(id);
            if (cozinha != null) {
                cozinhaRepository.remover(cozinha);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
