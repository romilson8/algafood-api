package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.auth.Cozinha;
import com.algaworks.algafood.domain.model.auth.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaServiceImpl implements CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Override
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @Override
    public Cozinha buscar(Long id) {
        return cozinhaRepository.buscar(id);
    }

    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }


    @Override
    public void remover(Long id) {
        try {
            cozinhaRepository.remover(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(String.format(
                    "Não existe uma string de cozinha com codigo d" + id
            ));
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(String.format(
                    "Cozinha de código d não pode ser removida, pois está em uso" + id
            ));
        }
    }
}
