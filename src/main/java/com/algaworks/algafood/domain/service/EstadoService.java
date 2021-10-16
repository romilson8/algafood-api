package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoService {

    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Long id);
}
