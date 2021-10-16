package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.app.Cidade;
import com.algaworks.algafood.domain.model.app.repository.CidadeRepository;
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
        return cidadeRepository.findAll();
    }

    @Override
    public Cidade buscar(Long id) {
        return cidadeRepository.findById(id).orElseThrow( () -> new IllegalArgumentException(" Objeto não encontrado "));
    }

    @Override
    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @Override
    public void remover(Long id) {
        try {
            Cidade cidade = this.buscar(id);
            cidadeRepository.delete(cidade);
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
        Cidade cidadeEncontrada = cidadeRepository.findById(id).get();
        if(cidadeEncontrada == null){
            throw new EntidadeNaoEncontradaException(String.format("" +
                    "Cidade com codigo %d não encontrado na base", id));
        }
        BeanUtils.copyProperties(cidade, cidadeEncontrada, "id");
        return cidadeRepository.save(cidade);
    }
}
