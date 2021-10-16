package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeService {

    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar(Cidade cidade);
    void remover(Long id);
    Cidade atualizar (Long id, Cidade cidade);
}
