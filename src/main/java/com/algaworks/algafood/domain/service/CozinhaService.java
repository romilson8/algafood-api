package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.auth.Cozinha;

import java.util.List;

public interface CozinhaService {

    List<Cozinha> listar();
    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Long id);


}
