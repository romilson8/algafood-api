package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Override
    public Restaurante buscar(Long id) {
        return restauranteRepository.buscarRestaurante(id);
    }

    @Override
    public List<Restaurante> listar() {
        return restauranteRepository.listarRestaurantes();
    }

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscar(cozinhaId);
        if(cozinha == null){
            throw new EntidadeNaoEncontradaException(String.format(
                    "Não existe cozinha cadastrada com id %d",  cozinhaId
            ));
        }
        return restauranteRepository.criarRestaurante(restaurante);
    }
}
