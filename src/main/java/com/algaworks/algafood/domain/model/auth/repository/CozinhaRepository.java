package com.algaworks.algafood.domain.model.auth.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.auth.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> listar();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);
	
}
