package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Restaurante criarRestaurante(Restaurante restaurante) {
        return entityManager.merge(restaurante);
    }

    @Override
    public List<Restaurante> listarRestaurantes() {
        return entityManager.createQuery("from Restaurante", Restaurante.class)
                .getResultList();
    }

    @Override
    public Restaurante buscarRestaurante(Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Override
    public void removerRestaurante(Restaurante restaurante) {
        restaurante = buscarRestaurante(restaurante.getId());
        entityManager.remove(restaurante);
    }
}
