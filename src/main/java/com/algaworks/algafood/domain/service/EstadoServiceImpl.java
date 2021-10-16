package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @Override
    public Estado buscar(Long id) {
        return estadoRepository.buscar(id);
    }

    @Override
    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    @Override
    public void remover(Long id) {
        try {
            estadoRepository.remover(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(String.format(
                    "Não existe uma string de Estado com codigo d" + id
            ));
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(String.format(
                    "Estado de código d não pode ser removida, pois está em uso" + id
            ));
        }
    }
}
