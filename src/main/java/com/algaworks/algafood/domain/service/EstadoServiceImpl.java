package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.app.Estado;
import com.algaworks.algafood.domain.model.app.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @Override
    public Estado buscar(Long id) {
        return estadoRepository.findById(id).orElseThrow( () -> new IllegalArgumentException(" Objeo não encontrado"));
    }

    @Override
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Override
    public void remover(Long id) {
        try {
            Estado estado = this.buscar(id);
            estadoRepository.delete(estado);
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
