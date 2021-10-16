package com.algaworks.algafood.domain.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl implements CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    @Override
    public Cidade buscar(Long id) {
        return cidadeRepository.buscar(id);
    }

    @Override
    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.salvar(cidade);
    }

    @Override
    public void remover(Long id) {
        try {
            cidadeRepository.remover(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(String.format(
                    "Não existe uma string de Cidade com codigo d" + id
            ));
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(String.format(
                    "Cidade de código d não pode ser removida, pois está em uso" + id
            ));
        }
    }

    @Override
    public Cidade atualizar(Long id, Cidade cidade) {
        Cidade cidadeEncontrada = cidadeRepository.buscar(id);
        if(cidadeEncontrada == null){
            throw new EntidadeNaoEncontradaException(String.format("" +
                    "Cidade com codigo %d não encontrado na base", id));
        }
        BeanUtils.copyProperties(cidade, cidadeEncontrada, "id");
        return cidadeRepository.salvar(cidade);
    }
}
