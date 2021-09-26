package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteService {

    Restaurante buscar(Long id);

    List<Restaurante> listar();

    Restaurante salvar(Restaurante restaurante);
}
