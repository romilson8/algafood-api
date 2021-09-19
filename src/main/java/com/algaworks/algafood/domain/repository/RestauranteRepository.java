package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    Restaurante criarRestaurante(Restaurante restaurante);

    List<Restaurante> listarRestaurantes();

    Restaurante buscarRestaurante(Long id);

    void removerRestaurante(Restaurante restaurante);
}
