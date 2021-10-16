package com.algaworks.algafood.domain.model.app.repository;

import com.algaworks.algafood.domain.model.app.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Long id);
}
