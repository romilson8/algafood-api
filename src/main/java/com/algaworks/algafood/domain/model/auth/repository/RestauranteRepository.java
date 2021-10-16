package com.algaworks.algafood.domain.model.auth.repository;

import com.algaworks.algafood.domain.model.auth.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    Restaurante criarRestaurante(Restaurante restaurante);

    List<Restaurante> listarRestaurantes();

    Restaurante buscarRestaurante(Long id);

    void removerRestaurante(Restaurante restaurante);
}
