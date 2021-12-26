package com.algaworks.algafood.models;

import com.algaworks.algafood.domain.model.app.Cidade;

import static com.algaworks.algafood.models.EstadoData.estadoMock;

public class CidadeData {
    public static Cidade cidadeMock() {
        return Cidade
                .builder()
                .id(1l)
                .nome("Recife")
                .estado(estadoMock())
                .build();
    }
}
