package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public Restaurante buscar(Long id) {
        return restauranteRepository.buscarRestaurante(id);
    }

    @Override
    public List<Restaurante> listar() {
        return restauranteRepository.listarRestaurantes();
    }
}
